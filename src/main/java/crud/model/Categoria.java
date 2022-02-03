package crud.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter @Setter @ToString
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
}
