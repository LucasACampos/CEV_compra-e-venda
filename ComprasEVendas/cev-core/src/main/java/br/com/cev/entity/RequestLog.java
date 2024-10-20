package br.com.cev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REQUEST_LOG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestLog {

    @Id
    @SequenceGenerator(name = "request_log_seq", sequenceName = "request_log_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "request_log_seq")
    private Long id;

    @Column
    private String endpoint;

    @Column(columnDefinition = "TEXT")
    private String argumentos;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @Column
    private UUID requestId;


    public static final class RequestLogBuilder {
        private RequestLog requestLog;

        private RequestLogBuilder() {
            requestLog = new RequestLog();
        }

        public static RequestLogBuilder aRequestLog() {
            return new RequestLogBuilder();
        }

        public RequestLogBuilder withId(Long id) {
            requestLog.setId(id);
            return this;
        }

        public RequestLogBuilder withEndpoint(String endpoint) {
            requestLog.setEndpoint(endpoint);
            return this;
        }

        public RequestLogBuilder withResponse(String response) {
            requestLog.setResponse(response);
            return this;
        }

        public RequestLogBuilder withHorario(Date horario) {
            requestLog.setHorario(horario);
            return this;
        }

        public RequestLogBuilder withArgumentos(String argumentos) {
            requestLog.setArgumentos(argumentos);
            return this;
        }

        public RequestLogBuilder withRequestId(UUID requestId) {
            requestLog.setRequestId(requestId);
            return this;
        }

        public RequestLog build() {
            return requestLog;
        }
    }
}
