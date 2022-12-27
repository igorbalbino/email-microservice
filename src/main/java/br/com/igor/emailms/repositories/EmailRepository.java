package br.com.igor.emailms.repositories;

import br.com.igor.emailms.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
