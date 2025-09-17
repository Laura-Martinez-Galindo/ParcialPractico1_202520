package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanetaSistemaSolarService {
    @Autowired
    PlanetaRepository planetaRepository;
    @Autowired
    SistemaSolarRepository sistemaSolarRepository;

    @Transactional
    public PlanetaEntity addSistemaSolar(Long planetaId, Long sistemaSolarId) throws EntityNotFoundException, IllegalOperationException{
        Optional<PlanetaEntity> planetaOptional = planetaRepository.findById(planetaId);
        if (planetaOptional.isEmpty()){
            throw new EntityNotFoundException("No se encontro el planeta");
        }
        Optional<SistemaSolar> sistemaSolarOptional = sistemaSolarRepository.findById(sistemaSolarId);
        if (sistemaSolarOptional.isEmpty()){
            throw new EntityNotFoundException("No se encontro el sistema solar");
        }

        PlanetaEntity planeta = planetaOptional.get();
        SistemaSolar sistemaSolar = sistemaSolarOptional.get();

        Integer poblacionTotal = 0;
        for (PlanetaEntity planetaSistemaSolar : sistemaSolar.getPlanetas()){
            poblacionTotal = poblacionTotal + planetaSistemaSolar.getPoblacion();
        }
        poblacionTotal = poblacionTotal + planeta.getPoblacion();
        Double ratioActual = Double.valueOf(sistemaSolar.getNumStormtroopers()/poblacionTotal);

        if(ratioActual < sistemaSolar.getRatioFuerza()){
            throw new IllegalOperationException("El ratio actual debe ser mayor al mínimo");
        }

        planeta.setSistemaSolar(sistemaSolar);
        sistemaSolar.getPlanetas().add(planeta);
        return planeta;
    }
}
