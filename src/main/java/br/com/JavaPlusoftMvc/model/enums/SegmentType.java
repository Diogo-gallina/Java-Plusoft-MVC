package br.com.JavaPlusoftMvc.model.enums;

public enum SegmentType {
    HEALTH("Saúde"), FINANCIAL("Financeiro"), EDUCATION("Educação"), ULTILITIES("Utilidades"),
    AGRIBUSINESS("Agronegocio"), TELECOM("Telecomunicação"), SERVICES("Serviços"),
    IT("Informática"), RETAIL("Varejo"), SECURE("Seguro");

    private String label;

    SegmentType(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
