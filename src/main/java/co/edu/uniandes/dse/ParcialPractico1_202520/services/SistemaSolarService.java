package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SistemaSolarService {
    @Autowired
    private SistemaSolarRepository sistemaSolarRepository;

    @Transactional
    public SistemaSolar createSistemaSolar(SistemaSolar sistemaSolar) throws IllegalOperationException{
        if (sistemaSolar.getNombre().length() >= 31){
            throw new IllegalOperationException("El nombre de un sistema solar debe tener ser menor a los 31 caracteres");
        }
        if (sistemaSolar.getRatioFuerza() < 0.2 || sistemaSolar.getRatioFuerza()>0.6){
            throw new IllegalOperationException("El ratio del sistema solar debe estar en un rango mayor o igual a 0,2 y menor o igual a 0,6");
        }
        if (sistemaSolar.getNumStormtroopers() <= 1000){
            throw new IllegalOperationException("El número de Stormtroopers asignados a un sistema solar debe ser mayor a 1000 unidades");
        }
        return sistemaSolarRepository.save(sistemaSolar);
    }


}
