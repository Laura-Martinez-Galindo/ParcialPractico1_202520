package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import org.hibernate.query.IllegalQueryOperationException;
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
        if (!((planeta.getNombre()).equals("I") || planeta.getNombre().equals("II") || planeta.getNombre().equals("III"))){
            throw new IllegalOperationException("El nombre de un sistema solar debe tener ser menor a los 31 caracteres");
        }
        if (planeta.getPoblacion() > 0){
            throw new IllegalOperationException("null");
        }
        return planetaRepository.save(planeta);
        
    }

}
