package com.daw.parcelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "parcela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{parcela.nombre.obligatorio}")
    @Size(max = 60, message = "{parcela.nombre.longitud}")
    private String nombre;

    @NotBlank(message = "{parcela.ubicacion.obligatoria}")
    @Size(max = 80, message = "{parcela.ubicacion.longitud}")
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @NotNull(message = "{parcela.superficie.obligatoria}")
    @DecimalMin(value = "0.01", message = "{parcela.superficie.rango}")
    @DecimalMax(value = "5000.00", message = "{parcela.superficie.rango}")
    @Column(nullable = false)
    private Double superficie;

    @NotNull(message = "{parcela.cultivo.obligatorio}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_cultivo")
    private TipoCultivo tipoCultivo;
}



