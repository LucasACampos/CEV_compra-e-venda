package br.com.cev.aspect;

import br.com.cev.entity.RequestLog;
import br.com.cev.repository.RequestLogRepository;
import br.com.cev.request.filters.RequestIDFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class RequestLogAspect {

    private final RequestLogRepository requestLogRepository;

    public RequestLogAspect(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Around("execution(* br.com.cev.restcontrollers.*.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {

        RequestLog.RequestLogBuilder requestLogBuilder = RequestLog.RequestLogBuilder.aRequestLog();

        requestLogBuilder.withHorario(new Date());

        Object joinPointResult = joinPoint.proceed();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String response;
        if(joinPointResult != null){
            response = objectWriter.writeValueAsString(joinPointResult);
        }else {
            response = null;
        }

        requestLogBuilder.withArgumentos(
                joinPoint.getArgs() != null ? objectWriter.writeValueAsString(joinPoint.getArgs()) : null
        );

        requestLogBuilder.withResponse(
                response
        );

        requestLogBuilder.withEndpoint(
                joinPoint.getSignature().getName()
        );

        requestLogBuilder.withRequestId(
                UUID.fromString(MDC.get(RequestIDFilter.REQUEST_ID_HEADER_NAME))
        );

        requestLogRepository.save(
                requestLogBuilder.build()
        );

        return joinPointResult;
    }
}
