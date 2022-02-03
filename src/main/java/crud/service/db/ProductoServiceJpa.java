package crud.service.db;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.model.Producto;
import crud.repository.ProductoRepository;
import crud.service.IProductoService;

// Indicamos que esta clase implementaremos la interface IProductoService con el fin de implementar sus metodos utilizando el repositorio JPA
// extendido en ProductoRepository
@Service // Indicamos que es una clase de servicio
public class ProductoServiceJpa implements IProductoService {

    @Autowired // Indicamos con esta anotacion que inyectaremos ProductoRepository en esta
               // clase de servicio con el fin de utilizar sus metodos
    ProductoRepository repoProducto;

    @Override
    public Page<Producto> listarProducto(Pageable page) {
        return repoProducto.findAll(page);
    }

    @Override
    public Producto buscarPorId(Integer idProducto) throws Exception {
        return repoProducto.findById(idProducto).orElseThrow(() -> new Exception("El producto no existe"));
    }

    @Override
    public void guardar(Producto producto) {
        repoProducto.save(producto);
    }

    @Override
    public void eliminar(Integer idProducto) {
        repoProducto.deleteById(idProducto);        
    }

    
	@Transactional
	@Override
	public int bloquear(int idProducto) {
		int rows = repoProducto.lock(idProducto);
		return rows;
	}

	@Transactional
	@Override
	public int activar(int idProducto) {
		int rows = repoProducto.unlock(idProducto);
		return rows;
	}

}
