package br.com.JavaPlusoftMvc.dto.employe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEmployeeDTO(

        Long id,

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        String email
) {
}