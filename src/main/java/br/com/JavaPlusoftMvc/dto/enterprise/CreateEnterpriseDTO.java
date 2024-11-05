package br.com.JavaPlusoftMvc.dto.enterprise;

import br.com.JavaPlusoftMvc.model.enums.SegmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEnterpriseDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        String name,

        @NotBlank(message = "CNPJ não pode ser vazio")
        @Size(min = 14, max = 14, message = "CNPJ deve conter 14 caracteres")
        String cnpj,

        @NotNull(message = "Tipo de segmento não pode ser vazio")
        SegmentType segmentType

){
}
