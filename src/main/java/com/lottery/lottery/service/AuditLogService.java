package com.lottery.lottery.service;

import com.lottery.lottery.constant.ApiConstants;
import com.lottery.lottery.dto.responses.AuditLogResponse;
import com.lottery.lottery.dto.responses.MessageResponse;
import com.lottery.lottery.entity.AuditLog;
//import com.lottery.lottery.mapper.DTOMapper;
import com.lottery.lottery.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;
//    private final DTOMapper dtoMapper;

    public void saveAuditLog(AuditLog auditLog) {
        try {
            auditLogRepository.save(auditLog);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save audit log: " + ex.getMessage());
        }
    }

    public ResponseEntity<MessageResponse<List<AuditLogResponse>>> getAllAuditLogs() {
        try {
            List<AuditLog> auditLogs = auditLogRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
//            List<AuditLogResponse> auditLogResponseList = auditLogs.stream()
//                    .map(dtoMapper::auditLogToAuditLogResponse)
//                    .toList();
            List<AuditLogResponse> auditLogResponseList = new ArrayList<>();
            for(AuditLog auditLog : auditLogs){
                AuditLogResponse auditLogResponse = new AuditLogResponse();
                auditLogResponse.setId(auditLog.getId());
                auditLogResponse.setEndpoint(auditLog.getEndpoint());
                auditLogResponse.setDuration(auditLog.getDuration());
                auditLogResponse.setStatus(auditLog.getStatus());
                auditLogResponse.setMethod(auditLog.getMethod());
                auditLogResponse.setTimestamp(auditLog.getTimestamp());
                auditLogResponseList.add(auditLogResponse);
            }

            return ResponseEntity.ok(
                    new MessageResponse(ApiConstants.STATUS_SUCCESS,
                            "Audit logs retrieved successfully", auditLogResponseList)
            );
        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve audit logs: " + ex.getMessage());
        }
    }
}
