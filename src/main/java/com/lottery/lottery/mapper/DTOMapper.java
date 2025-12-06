package com.lottery.lottery.mapper;

import com.lottery.lottery.dto.responses.AuditLogResponse;
import com.lottery.lottery.entity.AuditLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOMapper {
    AuditLogResponse auditLogToAuditLogResponse(AuditLog auditLog);
}
