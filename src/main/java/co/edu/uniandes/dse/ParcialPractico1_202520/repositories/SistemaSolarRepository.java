package co.edu.uniandes.dse.ParcialPractico1_202520.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import java.util.List;


public interface SistemaSolarRepository extends JpaRepository<SistemaSolar, Long>{
    List<SistemaSolar> findByNombre(String nombre);
    List<SistemaSolar> findByRegion(String region);
    List<SistemaSolar> findByRatioFuerza(Double ratioFuerza);
    List<SistemaSolar> findByNumStormtroopers(int numStormtroopers);
}
