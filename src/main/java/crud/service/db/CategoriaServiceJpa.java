package crud.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.model.Categoria;
import crud.repository.CategoriaRepository;
import crud.service.ICategoriaService;
@Service
public class CategoriaServiceJpa implements ICategoriaService {

    @Autowired
    CategoriaRepository repoCategoria;

    @Override
    public Page<Categoria> listarCategoria(Pageable page) {
        return repoCategoria.findAll(page);
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) throws Exception {
        return repoCategoria.findById(idCategoria).orElseThrow(() -> new Exception("Categoria no encontrada"));
    }

    @Override
    public void guardar(Categoria categoria) {
        repoCategoria.save(categoria);
    }

    @Override
    public void eliminar(Integer idCategoria) {
        repoCategoria.deleteById(idCategoria);
    }

}
