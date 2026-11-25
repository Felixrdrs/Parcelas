package com.daw.parcelas.service;


import com.daw.parcelas.model.Produccion;
import com.daw.parcelas.repository.ProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduccionServiceImpl implements ProduccionService {

    private final ProduccionRepository produccionRepository;

    public ProduccionServiceImpl(ProduccionRepository produccionRepository) {
        this.produccionRepository = produccionRepository;
    }

    @Override
    public List<Produccion> getAllProducciones() {
        return produccionRepository.findAll();
    }

    @Override
    public Produccion getProduccionById(Long id) {
        return produccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producción con id " + id + " no encontrada"));
    }

    @Override
    public void saveProduccion(Produccion produccion) {
        produccionRepository.save(produccion);
    }

    @Override
    public void deleteProduccionById(Long id) {
        produccionRepository.deleteById(id);
    }
}
