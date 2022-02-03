package crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import crud.model.Categoria;

public interface ICategoriaService {
    
    Page<Categoria> listarCategoria (Pageable page);
    Categoria buscarPorId (Integer idCategoria) throws Exception;
    void guardar (Categoria categoria);
    void eliminar (Integer idCategoria);
}
