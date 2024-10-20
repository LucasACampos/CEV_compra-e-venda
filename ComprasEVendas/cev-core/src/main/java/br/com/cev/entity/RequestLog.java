package br.com.cev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "REQUEST_LOG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String endpoint;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;


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

        public RequestLog build() {
            return requestLog;
        }
    }
}
