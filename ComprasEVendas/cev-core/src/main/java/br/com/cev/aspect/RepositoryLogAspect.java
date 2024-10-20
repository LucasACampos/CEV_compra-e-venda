package br.com.cev.aspect;

import br.com.cev.entity.RepositoryLog;
import br.com.cev.repository.RepositoryLogRepository;
import br.com.cev.request.filters.RequestIDFilter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class RepositoryLogAspect {

    private final RepositoryLogRepository repositoryLogRepository;

    public RepositoryLogAspect(RepositoryLogRepository repositoryLogRepository) {
        this.repositoryLogRepository = repositoryLogRepository;
    }

    @Around("execution(* br.com.cev.repository.*.*(..))"
            + " && " +
            "!( within(br.com.cev.repository.RepositoryLogRepository+) || within(br.com.cev.repository.RequestLogRepository+) )" )
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable{

        RepositoryLog.RepositoryLogBuilder repositoryLogBuilder =
                RepositoryLog.RepositoryLogBuilder.aRepositoryLog();

        repositoryLogBuilder.withHorario(new Date());

        Object joinPointResult = joinPoint.proceed();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        String result = null;

        try {
            if(joinPointResult != null){
                result = objectWriter.writeValueAsString(joinPointResult);
            }
        }catch (JsonMappingException e){
            if(!(e.getCause() instanceof EntityNotFoundException)){
                throw e;
            }
        }

        repositoryLogBuilder.withArgumentos(
                joinPoint.getArgs() != null ?
                        objectWriter.writeValueAsString(joinPoint.getArgs()) :
                        null
        );

        repositoryLogBuilder.withResponse(
                result
        );

        repositoryLogBuilder.withMetodo(
                joinPoint.getSignature().getName()
        );

        repositoryLogBuilder.withRequestId(
                UUID.fromString(MDC.get(RequestIDFilter.REQUEST_ID_HEADER_NAME))
        );

        repositoryLogRepository.save(
                repositoryLogBuilder.build()
        );

        return joinPointResult;
    }
}
