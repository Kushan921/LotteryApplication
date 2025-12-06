package com.lottery.lottery.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryRequest {
    private String name;
    private String mobile;
    private String ticketSequence;
}
