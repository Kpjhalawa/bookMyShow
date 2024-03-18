package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BookShowRequestDto {
   private Long userId;
   private List<Long> showSeatIds;
   private Long showId;
}
