package crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import crud.model.Producto;

public interface IProductoService  { // Es una interface donde se define los metodos y luego se implementan en el Controlador

    // METODOS CRUD 
    Page<Producto> listarProducto (Pageable page); // Indicamos que listaremos los productos en paginas personalizadas
    Producto buscarPorId (Integer idProducto) throws Exception; // Indicamos que vamos a buscar un producto mediante su ID en este caso nos regresara todo el producto
    void guardar (Producto producto); // Indicamos que vamos a guardar un producto en la BD
    void eliminar (Integer IdProducto); // Indicamos que vamos a eliminar un producto mediante su ID
    int bloquear(int idProducto);
	int activar(int idProducto);
   
}
