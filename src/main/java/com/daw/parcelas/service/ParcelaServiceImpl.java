
package com.daw.agrot.service;

import com.daw.agrot.entities.Parcela;
import com.daw.agrot.repository.ParcelaRepository;
import com.daw.parcelas.service.ParcelaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParcelaServiceImpl implements ParcelaService {

    private final ParcelaRepository parcelaRepository;

    public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    @Override
    public List<Parcela> getAllParcelas() {
        return parcelaRepository.findAll();
    }

    @Override
    public Parcela getParcelaById(Long id) {
        return parcelaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe la parcela con id " + id));
    }

    @Override
    public void createParcela(Parcela parcela) {
        parcelaRepository.save(parcela);
    }

    @Override
    public void deleteParcelaById(Long id) {
        parcelaRepository.deleteById(id);
    }
}



}
