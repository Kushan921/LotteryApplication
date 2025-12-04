package com.lottery.lottery.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryResponse {
    private String lotterySequence;
    private String drawnSequence;
    private double percentageWon;
    private double amountWon;
}
