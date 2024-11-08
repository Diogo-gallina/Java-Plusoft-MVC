package br.com.JavaPlusoftMvc.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCustomerDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 ccaracteres")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(max = 100, message = "Senha pode ter no máximo 100 ccaracteres")
        String password
) {
}
