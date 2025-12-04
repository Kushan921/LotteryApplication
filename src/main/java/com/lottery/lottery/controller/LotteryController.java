package com.lottery.lottery.controller;

import com.lottery.lottery.dto.requests.CreateEntryRequest;
import com.lottery.lottery.service.LotteryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lottery")
@RequiredArgsConstructor
public class LotteryController {
    private final LotteryService lotteryService;

    @GetMapping("/tickets")
    public ResponseEntity<?> getTickets() {
        return lotteryService.getTickets();
    }

    @PostMapping("/enter")
    public ResponseEntity<?> createEntry(@RequestBody CreateEntryRequest request,
                                         HttpServletRequest http) {
        return lotteryService.createEntry(request, http);
    }

    @GetMapping("/history/{mobile}")
    public ResponseEntity<?> getLotteryEntryByMobileNumber(@PathVariable String mobile) {
        return lotteryService.getLotteryEntryByMobileNumber(mobile);
    }
}
