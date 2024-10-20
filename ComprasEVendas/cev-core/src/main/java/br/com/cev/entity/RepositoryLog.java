package br.com.cev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REPOSITORY_LOG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RepositoryLog {

    @Id
    @SequenceGenerator(name = "repository_log_seq", sequenceName = "repository_log_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "repository_log_seq")
    private Long id;

    @Column
    private String metodo;

    @Column(columnDefinition = "TEXT")
    private String argumentos;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column
    private UUID requestId;

    public static final class RepositoryLogBuilder {
        private RepositoryLog repositoryLog;

        private RepositoryLogBuilder() {
            repositoryLog = new RepositoryLog();
        }

        public static RepositoryLogBuilder aRepositoryLog() {
            return new RepositoryLogBuilder();
        }

        public RepositoryLogBuilder withId(Long id) {
            repositoryLog.setId(id);
            return this;
        }

        public RepositoryLogBuilder withMetodo(String metodo) {
            repositoryLog.setMetodo(metodo);
            return this;
        }

        public RepositoryLogBuilder withArgumentos(String argumentos) {
            repositoryLog.setArgumentos(argumentos);
            return this;
        }

        public RepositoryLogBuilder withHorario(Date horario) {
            repositoryLog.setHorario(horario);
            return this;
        }

        public RepositoryLogBuilder withResponse(String response) {
            repositoryLog.setResponse(response);
            return this;
        }

        public RepositoryLogBuilder withRequestId(UUID requestId) {
            repositoryLog.setRequestId(requestId);
            return this;
        }

        public RepositoryLog build() {
            return repositoryLog;
        }
    }
}
