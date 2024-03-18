package com.example.demo.model.classes;

import com.example.demo.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel  {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus status;
   //to handle cancellation of booking then it,ll be m:m
    @OneToMany
    private List<ShowSeat> showSeatList;
    private int amount;
    @OneToMany
    private List<Payment> payments;

    @ManyToOne
    private User user;
}
