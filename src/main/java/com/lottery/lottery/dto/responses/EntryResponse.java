package com.lottery.lottery.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EntryResponse {
    private Long lotteryId;
    private String lotterySequence;
    private String drawnSequence;
    private double percentageWon;
    private double amountWon;
}
