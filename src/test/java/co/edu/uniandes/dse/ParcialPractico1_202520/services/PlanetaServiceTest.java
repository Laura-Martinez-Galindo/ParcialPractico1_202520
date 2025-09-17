package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({PlanetaService.class})
public class PlanetaServiceTest {
    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }

    void clearData(){
        entityManager.getEntityManager().createQuery("delete from PlanetaEntity.class").executeUpdate();
    }

    void insertData(){

    }

    @Test
    void testCreatePlaneta() throws IllegalOperationException{
        PlanetaEntity planeta = factory.manufacturePojo(PlanetaEntity.class);

        planeta.setNombre("nombreI");
        planeta.setPoblacion(30);
        planeta.setDiametro(22L);

        PlanetaEntity planetaCrear = planetaService.createPlaneta(planeta);
        assertNotNull(planetaCrear);
        //Ya esta ne base de datos
        PlanetaEntity planetaCreado = entityManager.find(PlanetaEntity.class, planetaCrear.getId());
        //reviso iguales
        assertEquals(planetaCreado.getId(), planeta.getId());
        assertEquals(planetaCreado.getNombre(), planeta.getNombre());
        assertEquals(planetaCreado.getPoblacion(), planeta.getPoblacion());
        assertEquals(planetaCreado.getDiametro(), planeta.getDiametro());
        
    }
}
