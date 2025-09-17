package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({PlanetaSistemaSolarService.class})
public class PlanetaSistemaSolarServiceTest {
    @Autowired
    private PlanetaSistemaSolarService planetaSistemaSolarService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PodamFactory factory = new PodamFactoryImpl();

    private List<PlanetaEntity> planetas = new ArrayList<>();
    private List<SistemaSolar> sistemas = new ArrayList<>();

    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }

    void clearData(){
        entityManager.getEntityManager().createQuery("delete from PlanetaEntity.class").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SistemaSolar.class").executeUpdate();
    }   

    void insertData(){
        for (int i = 0; i<2; i++){
            PlanetaEntity planeta = factory.manufacturePojo(PlanetaEntity.class);
            planeta.setNombre("nombreI");
            planeta.setPoblacion(30);
            planeta.setDiametro(22L);
            entityManager.persist(planeta);
            planetas.add(planeta);

        }

        for (int i = 0; i<2; i++){
            SistemaSolar sistemaSolar = factory.manufacturePojo(SistemaSolar.class);
            sistemaSolar.setNombre("nombre");
            sistemaSolar.setRegion("CORE");
            sistemaSolar.setRatioFuerza(0.3);
            sistemaSolar.setNumStormtroopers(60);
            entityManager.persist(sistemaSolar);
            sistemas.add(sistemaSolar);
        }
    }
    @Test
        void testAddSistemaSolar() throws IllegalOperationException, EntityNotFoundException{
            PlanetaEntity planeta = planetas.get(0);
            SistemaSolar sistemaSolar = sistemas.get(0);
            PlanetaEntity respuesta = planetaSistemaSolarService.addSistemaSolar(planeta.getId(), sistemaSolar.getId());

            assertNotNull(respuesta);
            assertEquals(respuesta.getId(), planeta.getId());
            assertTrue(sistemaSolar.getPlanetas().contains(planeta));
            
        }

    @Test
        void testAddInvalidPlaneta() throws IllegalOperationException, EntityNotFoundException{
            SistemaSolar sistemaSolar = sistemas.get(0);
            assertThrows(EntityNotFoundException.class, ()->{
                planetaSistemaSolarService.addSistemaSolar(22L, sistemaSolar.getId());
            });
            
        }


    @Test
        void testAddInvalidRatio() throws IllegalOperationException, EntityNotFoundException{
            SistemaSolar sistemaSolar = sistemas.get(0);
            PlanetaEntity planeta1 = planetas.get(0);
            
            planetaSistemaSolarService.addSistemaSolar(planeta1.getId(), sistemaSolar.getId());
            PlanetaEntity planeta2 = planetas.get(0);
            planetaSistemaSolarService.addSistemaSolar(planeta2.getId(), sistemaSolar.getId());


            PlanetaEntity planeta = factory.manufacturePojo(PlanetaEntity.class);
            planeta.setNombre("nombreI");
            planeta.setPoblacion(5000);
            planeta.setDiametro(22L);
            assertThrows(IllegalOperationException.class, ()->{
                planetaSistemaSolarService.addSistemaSolar(planeta.getId(), sistemaSolar.getId());
            });
            
        }

}
