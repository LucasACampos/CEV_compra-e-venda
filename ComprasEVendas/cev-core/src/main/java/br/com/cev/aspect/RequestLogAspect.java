package br.com.cev.aspect;

import br.com.cev.entity.RequestLog;
import br.com.cev.repository.RequestLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

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
            response = "null";
        }

        requestLogBuilder.withResponse(
                response
        );

        requestLogBuilder.withEndpoint(
                joinPoint.getSignature().getName()
        );

        requestLogRepository.save(
                requestLogBuilder.build()
        );

        return joinPointResult;
    }
}
