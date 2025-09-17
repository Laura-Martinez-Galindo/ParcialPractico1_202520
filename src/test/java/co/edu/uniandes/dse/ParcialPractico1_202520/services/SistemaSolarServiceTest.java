package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({SistemaSolarService.class})
public class SistemaSolarServiceTest {
    @Autowired
    private SistemaSolarService sistemaSolarService;

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
        entityManager.getEntityManager().createQuery("delete from SistemaSolar.class").executeUpdate();
    }

    void insertData(){

    }

    @Test
    void testCreateSistemaSolar() throws IllegalOperationException{
        SistemaSolar sistemaSolar = factory.manufacturePojo(SistemaSolar.class);

        sistemaSolar.setNombre("nombre");
        sistemaSolar.setRegion("CORE");
        sistemaSolar.setRatioFuerza(0.3);
        sistemaSolar.setNumStormtroopers(4);

        SistemaSolar sistemaSolarCrear =sistemaSolarService.createSistemaSolar(sistemaSolar);
        assertNotNull(sistemaSolarCrear);
        //Ya esta ne base de datos
        SistemaSolar sistemaSolarCreado = entityManager.find(SistemaSolar.class, sistemaSolarCrear.getId());
        //reviso iguales
        assertEquals(sistemaSolarCreado.getId(), sistemaSolar.getId());
        assertEquals(sistemaSolarCreado.getNombre(), sistemaSolar.getNombre());
        assertEquals(sistemaSolarCreado.getRegion(), sistemaSolar.getRegion());
        assertEquals(sistemaSolarCreado.getRatioFuerza(), sistemaSolar.getRatioFuerza());
        assertEquals(sistemaSolarCreado.getNumStormtroopers(), sistemaSolar.getNumStormtroopers()); 
    }

    @Test
    void testCreateSistemaSolarWithInvalidRate() throws IllegalOperationException{
        SistemaSolar sistemaSolar = factory.manufacturePojo(SistemaSolar.class);

        sistemaSolar.setNombre("nombre");
        sistemaSolar.setRegion("CORE");
        sistemaSolar.setRatioFuerza(0.1);
        sistemaSolar.setNumStormtroopers(4);

        assertThrows(IllegalOperationException.class, ()->{
            sistemaSolarService.createSistemaSolar(sistemaSolar);
        });
}


}
