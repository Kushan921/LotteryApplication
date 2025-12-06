package com.lottery.lottery.service;

import com.lottery.lottery.constant.ApiConstants;
import com.lottery.lottery.dto.requests.CreateEntryRequest;
import com.lottery.lottery.dto.responses.EntryResponse;
import com.lottery.lottery.dto.responses.HistoryResponse;
import com.lottery.lottery.dto.responses.MessageResponse;
import com.lottery.lottery.dto.responses.TicketResponse;
import com.lottery.lottery.entity.LotteryEntry;
import com.lottery.lottery.exception.ResourceNotFoundException;
import com.lottery.lottery.repository.LotteryEntryRepository;
import com.lottery.lottery.util.RandomGeneratorUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotteryService {
    private final LotteryEntryRepository lotteryEntryRepository;

    private static final double JACKPOT = 1000.0;

    // pre-populated tickets
    private final List<String> prePopulatedTickets = new ArrayList<>();

    // Generate tickets when service starts
    @PostConstruct
    public void initTickets() {
        for (int i = 0; i < 10; i++) {
            prePopulatedTickets.add(RandomGeneratorUtil.generateSequence());
        }
    }

    public ResponseEntity<MessageResponse<List<TicketResponse>>> getTickets() {
        try {
            List<TicketResponse> tickets = new ArrayList<>();
            for (String t : prePopulatedTickets) {
                tickets.add(new TicketResponse(t));
            }

            return ResponseEntity.ok(
                    new MessageResponse(ApiConstants.STATUS_SUCCESS,
                            "Tickets retrieved successfully", tickets)
            );
        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve tickets: " + ex.getMessage());
        }
    }

    public ResponseEntity<MessageResponse<EntryResponse>> createEntry(CreateEntryRequest request, HttpServletRequest http) {
        try {
            String drawn = RandomGeneratorUtil.generateSequence();
            String userTicket = request.getTicketSequence();

            double percentage = calculatePercentage(userTicket, drawn);
            double amountWon = (JACKPOT * percentage) / 100.0;

            LotteryEntry entry = LotteryEntry.builder()
                    .name(request.getName())
                    .mobile(request.getMobile())
                    .lotterySequence(userTicket)
                    .drawnSequence(drawn)
                    .percentageWon(percentage)
                    .amountWon(amountWon)
                    .ipAddress(http.getRemoteAddr())
                    .userAgent(http.getHeader("User-Agent"))
                    .createdAt(LocalDateTime.now())
                    .build();

            lotteryEntryRepository.save(entry);

            EntryResponse entryResponse = new EntryResponse();
            entryResponse.setLotteryId(entry.getId());
            entryResponse.setLotterySequence(userTicket);
            entryResponse.setDrawnSequence(drawn);
            entryResponse.setPercentageWon(percentage);
            entryResponse.setAmountWon(amountWon);

            return ResponseEntity.ok(
                    new MessageResponse(ApiConstants.STATUS_SUCCESS,
                            "Entry Created Successfully", entryResponse)
            );
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create lottery entry: " + ex.getMessage());
        }
    }

    private double calculatePercentage(String ticket, String drawn) {
        try {
            String[] t = ticket.split(" ");
            String[] d = drawn.split(" ");

            int correctRightPosition = 0;
            int correctWrongPosition = 0;

            for (int i = 0; i < 4; i++) {
                if (t[i].equals(d[i])) correctRightPosition++;
                else if (Arrays.asList(d).contains(t[i])) correctWrongPosition++;
            }

            boolean letterMatch = t[4].equals(d[4]);

            return (correctRightPosition * 20)
                    + (correctWrongPosition * 10)
                    + (letterMatch ? 10 : 0);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to calculate winning percentage: " + ex.getMessage());
        }
    }

    public ResponseEntity<MessageResponse<List<HistoryResponse>>> getLotteryEntryByMobileNumber(String mobile) {
        try {
            List<HistoryResponse> historyResponse = lotteryEntryRepository.findByMobile(mobile)
                    .stream()
                    .map(e -> HistoryResponse.builder()
                            .lotterySequence(e.getLotterySequence())
                            .drawnSequence(e.getDrawnSequence())
                            .percentageWon(e.getPercentageWon())
                            .amountWon(e.getAmountWon())
                            .build())
                    .toList();

            if (historyResponse.isEmpty()) {
                throw new ResourceNotFoundException("No lottery entries found for mobile: " + mobile);
            }

            return ResponseEntity.ok(
                    new MessageResponse(ApiConstants.STATUS_SUCCESS,
                            "History retrieved successfully", historyResponse)
            );
        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve history: " + ex.getMessage());
        }
    }
}
