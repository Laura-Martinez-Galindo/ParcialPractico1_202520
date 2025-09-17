package co.edu.uniandes.dse.ParcialPractico1_202520.entities;


import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
public class PlanetaEntity extends BaseEntity{
    private String nombre;
    private Integer poblacion;
    private Long diametro;

    @PodamExclude
    @ManyToOne
    private SistemaSolar sistemaSolar;
}
