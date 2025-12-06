package com.lottery.lottery.dto.responses;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntryResponse {
    private Long lotteryId;
    private String lotterySequence;
    private String drawnSequence;
    private double percentageWon;
    private double amountWon;
}
