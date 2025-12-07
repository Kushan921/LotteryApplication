package com.lottery.lottery.mapper;

import com.lottery.lottery.dto.responses.AuditLogResponse;
import com.lottery.lottery.entity.AuditLog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T22:07:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DTOMapperImpl implements DTOMapper {

    @Override
    public AuditLogResponse auditLogToAuditLogResponse(AuditLog auditLog) {
        if ( auditLog == null ) {
            return null;
        }

        AuditLogResponse auditLogResponse = new AuditLogResponse();

        auditLogResponse.setId( auditLog.getId() );
        auditLogResponse.setEndpoint( auditLog.getEndpoint() );
        auditLogResponse.setMethod( auditLog.getMethod() );
        auditLogResponse.setStatus( auditLog.getStatus() );
        auditLogResponse.setDuration( auditLog.getDuration() );
        auditLogResponse.setTimestamp( auditLog.getTimestamp() );

        return auditLogResponse;
    }
}
