package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class SignUpUserDtoResponse {
private Long userId;
private ResponseStatus responseStatus;
}
