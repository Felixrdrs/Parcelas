package com.daw.parcelas.service;

import com.daw.parcelas.model.Produccion;

import java.util.List;

public interface ProduccionService {
    List<Produccion> getAllProducciones();
    Produccion getProduccionById(Long id);
    void saveProduccion(Produccion produccion);
    void deleteProduccionById(Long id);
}