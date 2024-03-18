package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class BookingShowResponseDto extends Throwable {
private Long BookingId;
private int amount;
private ResponseStatus responseStatus;
private String failureReason;


}
