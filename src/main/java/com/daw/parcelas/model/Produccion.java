package com.daw.parcelas.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "producciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El producto no puede estar vacío")
    private String producto;

    @NotNull(message = "La calidad no puede estar vacía")
    @Enumerated(EnumType.STRING)
    private Calidad calidad;

    //Puedo sembrar varios cultivos en una parcela, pero cada producción pertenece a una parcela concreta
    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

}
