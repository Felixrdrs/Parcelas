package com.daw.parcelas.service;




import com.daw.parcelas.excepcion.RecursoNoEncontradoException;
import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private static final Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Sensor> buscar(String q, TipoSensor tipo) {
        String filtro = (q == null) ? "" : q.trim().toLowerCase();

        return sensorRepository.findAll().stream()
                .filter(s -> filtro.isEmpty()
                        || (s.getName() != null && s.getName().toLowerCase().contains(filtro))
                        || (s.getModel() != null && s.getModel().toLowerCase().contains(filtro)))
                .filter(s -> tipo == null || s.getTipo() == tipo)
                .toList();
    }

    @Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Sensor con id " + id + " no encontrado"));
    }

    @Override
    public List<Sensor> getAllSensorsTemperatura() {
        logger.debug("Obteniendo todos los sensores de temperatura");
        return sensorRepository.findAllByTipoEquals(TipoSensor.TEMPERATURA);
    }

    @Override
    public Sensor guardar(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public void eliminar(Long id) {
        Sensor sensor = getSensorById(id);
        sensorRepository.delete(sensor);
    }
}
