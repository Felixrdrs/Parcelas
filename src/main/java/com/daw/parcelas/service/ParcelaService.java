package com.daw.parcelas.service;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;

import java.util.List;


public interface ParcelaService {

    List<Parcela> getAllParcels();

    double superficieTotal();

    List<Parcela> buscar(String texto, TipoCultivo cultivo);

    Parcela getParcelById(Long id);

    Parcela guardar(Parcela parcela);

    void eliminar(Long id);

}
