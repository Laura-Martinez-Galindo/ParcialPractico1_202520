package co.edu.uniandes.dse.ParcialPractico1_202520.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import java.util.List;


public interface PlanetaRepository extends JpaRepository<PlanetaEntity, Long>{
    List<PlanetaEntity> findByNombre(String nombre);
    List<PlanetaEntity> findByPoblacion(Long poblacion);
    List<PlanetaEntity> findByDiametro(Long diametro);
}
