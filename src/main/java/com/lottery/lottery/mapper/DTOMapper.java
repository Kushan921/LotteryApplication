package com.lottery.lottery.mapper;

import com.lottery.lottery.dto.responses.AuditLogResponse;
import com.lottery.lottery.entity.AuditLog;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring", imports = Instant.class)
public interface DTOMapper {
    /**
     * Audit Log
     **/
    AuditLogResponse auditLogToAuditLogResponse(AuditLog auditLog);
}
