
package com.daw.parcelas.service;


import com.daw.parcelas.excepcion.RecursoNoEncontradoException;
import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.repository.ParcelaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelaServiceImpl implements ParcelaService {

    private final ParcelaRepository parcelaRepository;

    public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    @Override
    public List<Parcela> getAllParcels() {
        return parcelaRepository.findAll();
    }

    @Override
    public double superficieTotal() {
        return parcelaRepository.findAll().stream()
                .mapToDouble(p -> p.getSuperficie() != null
                        ? p.getSuperficie() : 0.0)
                .sum();
    }

    public List<Parcela> getParcel(String texto, TipoCultivo cultivo) {
        String filtro = texto == null
                ? "" : texto.trim().toLowerCase();

        return parcelaRepository.findAll().stream()
                .filter(p -> {
                    if (filtro.isEmpty()) {
                        return true;
                    }

                    String nombre = p.getNombre() == null
                            ? "" : p.getNombre().toLowerCase();

                    String ubicacion = p.getUbicacion() == null
                            ? "" : p.getUbicacion().toLowerCase();

                    return nombre.contains(filtro)
                            || ubicacion.contains(filtro);
                })
                .filter(p -> cultivo == null
                        || cultivo.equals(p.getTipoCultivo()))
                .toList();
    }

    @Override
    public Parcela getParcelById(Long id) {
        return parcelaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Parcela con id " + id + " no encontrada"));
    }
    @Override
    public List<Parcela> buscar(String texto, TipoCultivo cultivo) {
        String filtro = texto == null
                ? "" : texto.trim().toLowerCase();

        return parcelaRepository.findAll().stream()
                .filter(p -> {
                    if (filtro.isEmpty()) {
                        return true;
                    }

                    String nombre = p.getNombre() == null
                            ? "" : p.getNombre().toLowerCase();

                    String ubicacion = p.getUbicacion() == null
                            ? "" : p.getUbicacion().toLowerCase();

                    return nombre.contains(filtro)
                            || ubicacion.contains(filtro);
                })
                .filter(p -> cultivo == null
                        || cultivo.equals(p.getTipoCultivo()))
                .toList();
    }

    @Override
    public Parcela guardar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    @Override
    public void eliminar(Long id) {
        Parcela existente = getParcelById(id);
        parcelaRepository.delete(existente);
    }
}




