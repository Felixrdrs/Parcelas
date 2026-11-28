package com.daw.parcelas.service;

import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllSensors();
    List<Sensor> buscar(String q, TipoSensor tipo);
    Sensor getSensorById(Long id);
    List<Sensor> getAllSensorsTemperatura();
    Sensor guardar(Sensor sensor);
    void eliminar(Long id);
}
