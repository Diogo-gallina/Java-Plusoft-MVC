package br.com.JavaPlusoftMvc.dto.enterprise;


import br.com.JavaPlusoftMvc.model.Enterprise;
import br.com.JavaPlusoftMvc.model.enums.SegmentType;

import java.time.LocalDateTime;

public record EnterpriseDetailsDTO(
        Long enterpriseId,
        String name,
        String cnpj,
        SegmentType segmentType,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public EnterpriseDetailsDTO(Enterprise enterprise){
        this(
                enterprise.getId(),
                enterprise.getName(),
                enterprise.getCnpj(),
                enterprise.getSegmentType(),
                enterprise.getCreatedAt(),
                enterprise.getUpdatedAt()
        );
    }

}
