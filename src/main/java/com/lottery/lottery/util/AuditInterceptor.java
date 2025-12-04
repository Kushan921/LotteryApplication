package com.lottery.lottery.util;

import com.lottery.lottery.entity.AuditLog;
import com.lottery.lottery.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuditInterceptor implements HandlerInterceptor {
    private final AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        AuditLog log = new AuditLog();
        log.setEndpoint(request.getRequestURI());
        log.setMethod(request.getMethod());
        log.setStatus(response.getStatus());
        log.setDuration(duration);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
