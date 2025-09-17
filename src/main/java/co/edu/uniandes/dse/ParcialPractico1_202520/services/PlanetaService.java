package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanetaService {
    @Autowired
    PlanetaRepository planetaRepository;
    

    @Transactional
    public PlanetaEntity createPlaneta(PlanetaEntity planeta) throws IllegalOperationException{
        if ((!planeta.getNombre().endsWith("I")) && (!planeta.getNombre().endsWith("II")) && (!planeta.getNombre().endsWith("III"))){
            throw new IllegalOperationException("El nombre de todo planeta debe terminar con los números romanos 1, 2 o 3");
        }
        if (planeta.getPoblacion() <= 0){
            throw new IllegalOperationException("La población del planeta debe ser un número positivo mayor que 0");
        }
        return planetaRepository.save(planeta);
    }

}
