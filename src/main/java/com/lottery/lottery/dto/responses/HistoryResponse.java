package com.lottery.lottery.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {
    private String lotterySequence;
    private String drawnSequence;
    private double percentageWon;
    private double amountWon;
}
