package br.com.JavaPlusoftMvc.repository;

import br.com.JavaPlusoftMvc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where LOWER(c.email) = LOWER(:email)")
    Customer findByEmail(String email);
}
