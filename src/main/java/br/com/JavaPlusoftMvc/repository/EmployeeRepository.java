package br.com.JavaPlusoftMvc.repository;

import br.com.JavaPlusoftMvc.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
