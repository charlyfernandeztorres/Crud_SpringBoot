package crud.model;

import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity // Indicamos que es una Entidad o Clase modelo o Objeto
@Table(name = "productos") // Indicamos el nombre de la tabla en la Base de Datos
@Getter @Setter @ToString // Indicamos con la dependencia Lombok con el fin de reducir codigo los metodos Getters, Setters y el metodo toString 
public class Producto {
    @Id // Indicamos que es una llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que esta propiedad "id" sera autoincrementable
    private Integer id;
    private String nombre;
    @OneToOne //Indicamos la relacion de uno a uno que tiene la tabla productos y categoria
    @JoinColumn(name = "idCategoria")  // Lo unimos con su llave primaria de la tabla Categoria
    private Categoria categoria;
    private String imagen;
    private Integer status;
    @Column(name = "fecha_registro") // Indicamos el nombre de la propiedad en la Base de Datos
    private Date fechaRegistro;

}
