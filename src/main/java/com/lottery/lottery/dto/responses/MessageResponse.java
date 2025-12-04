package com.lottery.lottery.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse<T> {
    private String status;
    private String message;
    private T data;
}
