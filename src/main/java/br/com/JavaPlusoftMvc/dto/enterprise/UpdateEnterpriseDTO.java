package br.com.JavaPlusoftMvc.dto.enterprise;

import br.com.JavaPlusoftMvc.model.enums.SegmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEnterpriseDTO(

        Long id,

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        String name,

        @NotBlank(message = "Tipo de segmento não pode ser vazio")
        @Size(max = 50, message = "Tipo de segmento pode ter no máximo 50 caracteres")
        SegmentType segmentType

){
}
