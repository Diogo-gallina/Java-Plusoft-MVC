package br.com.JavaPlusoftMvc.repository;

import br.com.JavaPlusoftMvc.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
