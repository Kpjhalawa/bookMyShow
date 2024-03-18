package com.example.demo.controller;

import com.example.demo.dtos.BookShowRequestDto;
import com.example.demo.dtos.BookingShowResponseDto;
import com.example.demo.dtos.ResponseStatus;
import com.example.demo.exceptions.ShowNotAvailable;
import com.example.demo.exceptions.ShowNotFound;
import com.example.demo.exceptions.userIsInvalid;
import com.example.demo.model.classes.Booking;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private static final String ÜSER_INVALID_MESSAGE = "user id is invalid";
    private static final String SHOW_INVALID_MESSAGE = "show id is invalidf" ;
    private static final String SOMETHING_WENT_WORNG = "Something went wrong";

    BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    public BookingShowResponseDto bookshow(BookShowRequestDto request) {
        try {
            Booking booking = bookingService.bookShow(request);
            return new BookingShowResponseDto(booking.getId(),booking.getAmount(),ResponseStatus.SUCCESS,"SUCCESS");
        } catch (userIsInvalid e) {
            return new BookingShowResponseDto(null,0,ResponseStatus.FAILURE,ÜSER_INVALID_MESSAGE);
        } catch (ShowNotFound e) {
            return new BookingShowResponseDto(null,0,ResponseStatus.FAILURE,SHOW_INVALID_MESSAGE);
        } catch (ShowNotAvailable e) {
            return new BookingShowResponseDto(null,0,ResponseStatus.FAILURE,SOMETHING_WENT_WORNG);
        }
    }
}
