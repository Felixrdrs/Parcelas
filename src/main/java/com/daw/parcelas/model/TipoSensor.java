package com.daw.parcelas.model;

public enum TipoSensor {
    TEMPERATURA("Temperatura"),
    HUMEDAD("Humedad"),
    PH("pH"),
    AGUA("Agua"),
    LUZ("Luz");

    private final String etiqueta;

    TipoSensor(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
