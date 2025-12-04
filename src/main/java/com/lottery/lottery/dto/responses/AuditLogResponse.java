package com.lottery.lottery.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogResponse {
    private Long id;
    private String endpoint;
    private String method;
    private int status;
    private long duration;
    private LocalDateTime timestamp;
}
