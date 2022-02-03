package crud.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import crud.model.Categoria;
import crud.service.ICategoriaService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("categorias")
public class CategoriaController {
    @Autowired
    ICategoriaService serviCategoria;

    @GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Categoria> lista = serviCategoria.listarCategoria(page);
        model.addAttribute("categorias", lista);
        return "categorias/listCategoria";
    }

    @GetMapping("/create")
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @PostMapping("save")
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "categorias/formCategoria"; 
        } else {
            serviCategoria.guardar(categoria);
            attributes.addFlashAttribute("msg", "Registro Exitoso");
            return "redirect:/categorias/index";
        }
    }

    @GetMapping("edit/{id}")
    public String editar (@PathVariable ("id") Integer idCategoria, Model model) throws Exception{
        Categoria categoria = serviCategoria.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria"; 
    }

    @GetMapping("delete/{id}")
    public String eliminar(@PathVariable ("id") Integer idCategoria, RedirectAttributes attributes){
        serviCategoria.eliminar(idCategoria);
        attributes.addFlashAttribute("msg", "Categoría Eliminada");
        return "redirect:/categorias/index";
    }

}
