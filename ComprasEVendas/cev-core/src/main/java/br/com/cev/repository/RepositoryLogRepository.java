package br.com.cev.repository;

import br.com.cev.entity.RepositoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryLogRepository extends JpaRepository<RepositoryLog, Long> {
}
