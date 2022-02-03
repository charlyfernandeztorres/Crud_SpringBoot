package crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crud.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> { // Extendemos de la clase JpaRepository
                                                                               // con el fin de utilizar sus metodos
                                                                               // crud entre otros

    // metodos creado manualmente
    @Modifying
    @Query("UPDATE Producto p SET p.status=0 WHERE p.id = :paramIdProducto")
    int lock(@Param("paramIdProducto") int idProducto);

    @Modifying
    @Query("UPDATE Producto p SET p.status=1 WHERE p.id = :paramIdProducto")
    int unlock(@Param("paramIdProducto") int idProducto);
}
