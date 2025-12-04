package com.lottery.lottery.dto.requests;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String name;
    private String mobile;
    private String ticketSequence;
}
