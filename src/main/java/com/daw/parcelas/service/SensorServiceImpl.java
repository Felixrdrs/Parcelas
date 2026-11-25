package com.daw.parcelas.service;


import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    public final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Sensor> getAllSensorsByNameAndTipo(String name, String tipo) {
        if (tipo.equals("TODOS")) {
            return sensorRepository.findAllByNameContainingIgnoreCase(name);
        } else {
            return sensorRepository.findAllByNameContainingIgnoreCaseAndTipoEquals(name, TipoSensor.valueOf(tipo));
        }
    }

    @Override
    public Sensor getSensorById(Long id) {
        return null;
    }

    @Override
    public List<Sensor> getAllSensorsTemperatura() {
        return List.of();
    }

    @Override
    public void saveSensor(Sensor sensor) {

    }

    @Override
    public void deleteSensorById(Long id) {

    }

    /*@Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Sensor con id " +
                                id + " no encontrado"));
    }

    @Override
    public List<Sensor> getAllSensorsTemperatura() {
        logger.debug("Obteniendo todos los sensores de temperatura");
        return sensorRepository.findAllByTipoEquals(TipoSensor.TEMPERATURA);
    }

    @Override
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensorById(Long id) {
        sensorRepository.deleteById(id);
    }*/
}
