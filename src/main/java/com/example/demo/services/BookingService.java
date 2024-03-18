package com.example.demo.services;

import com.example.demo.dtos.BookShowRequestDto;
import com.example.demo.dtos.BookingShowResponseDto;
import com.example.demo.exceptions.ShowNotAvailable;
import com.example.demo.exceptions.ShowNotFound;
import com.example.demo.exceptions.userIsInvalid;
import com.example.demo.model.classes.Booking;
import com.example.demo.model.classes.Show;
import com.example.demo.model.classes.ShowSeat;
import com.example.demo.model.classes.User;
import com.example.demo.model.enums.BookingStatus;
import com.example.demo.model.enums.SeatStatus;
import com.example.demo.repository.BookingRepositiory;
import com.example.demo.repository.ShowRepositiory;
import com.example.demo.repository.ShowSeatRepositiory;
import com.example.demo.repository.UserRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepositiory userRepositiory;
    private ShowSeatRepositiory showSeatRepositiory;
    private ShowRepositiory showRepositiory;
    private BookingRepositiory bookingRepositiory;

    @Autowired
    public BookingService(UserRepositiory userRepositiory, ShowSeatRepositiory showSeatRepositiory, ShowRepositiory showRepositiory, BookingRepositiory bookingRepositiory) {
        this.userRepositiory = userRepositiory;
        this.showSeatRepositiory = showSeatRepositiory;
        this.showRepositiory = showRepositiory;
        this.bookingRepositiory = bookingRepositiory;
    }
    public Booking bookShow(BookShowRequestDto request) throws userIsInvalid, ShowNotFound, ShowNotAvailable {
        // get user with userId
        Optional<User> user = userRepositiory.findById(request.getUserId());
        if(!user.isPresent()) {
            throw new userIsInvalid();
        }
        // get show by showId
        Optional<Show> show = showRepositiory.findById(request.getShowId());
        if(!show.isPresent()){
            throw new ShowNotFound();
        }

        List<ShowSeat> reverseShowsSeats = reverseShowsSeats(request.getShowSeatIds());

        return reserveBooking(request,user,reverseShowsSeats);
    }

    private Booking reserveBooking(BookShowRequestDto request, Optional<User> user, List<ShowSeat> reverseShowsSeats) {
        Booking booking = new Booking();
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculate(request.getUserId(),request.getShowId()));
        booking.setUser(user.get());
        booking.setShowSeatList(reverseShowsSeats);
        booking.setPayments(new ArrayList<>());

        return bookingRepositiory.save(booking);
    }

    /*
    feel free to calculate price the on the basis on intreset
     */
    private int priceCalculate(Long userId, Long showId) {

        //get the show
        //get the seats
        //for the seats_id
        return 0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reverseShowsSeats(List<Long> showSeatIds) throws ShowNotAvailable {
        //get List<showSeat> id with show id's
        List<ShowSeat> showSeats = showSeatRepositiory.findAllById(showSeatIds);

        // checking if any of the showSeats are alreasy reservedb -> Throw an error
        for(ShowSeat showSeat : showSeats){
            seatNotAvaliableForBooking(showSeat);
        }
        List<ShowSeat> reservedShowSeats = new ArrayList<>();
        for(ShowSeat showseat : showSeats){
            showseat.setStatus(SeatStatus.LOCKED);
            showseat.setLastupdate(new Date());
            reservedShowSeats.add(showSeatRepositiory.save(showseat));
        }
        return reservedShowSeats;
    }

    private boolean seatNotAvaliableForBooking(ShowSeat showSeat) throws ShowNotAvailable {
        // refactor and make it understandeable
        /*
        here we do locking only if
        1.all the seatsare available OR
        2.if all the seats are locked and lockedDuration > 10
         */
        if(!SeatStatus.AVALIBALE.equals(showSeat.getStatus()));{
            if(SeatStatus.BOOKED.equals(showSeat.getStatus())){
                throw new ShowNotAvailable();
            }
            if(SeatStatus.LOCKED.equals(showSeat.getStatus())){
                Long lockedDuration = Duration.between(ShowSeat.getLockedAt().toInstant(),new Date().toInstant()).toMinutes();
                if(lockedDuration<10){
                    throw new ShowNotAvailable();
                }
            }
        }
        return true;
    }
}
