package com.lottery.lottery.controller;

import com.lottery.lottery.dto.responses.AuditLogResponse;
import com.lottery.lottery.dto.responses.MessageResponse;
import com.lottery.lottery.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<MessageResponse<List<AuditLogResponse>>> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs();
    }

}
