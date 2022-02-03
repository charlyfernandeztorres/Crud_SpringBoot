package crud.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import crud.model.Producto;
import crud.service.ICategoriaService;
import crud.service.IProductoService;
import crud.util.Utileria;

@Controller
@RequestMapping("productos")
public class ProductoController {

    @Value("${tesis.ruta.imagenes}")
    private String ruta;

    @Autowired
    IProductoService serviProducto;

    @Autowired
    ICategoriaService serviCategoria;

    @GetMapping("index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Producto> producto = serviProducto.listarProducto(page);
        model.addAttribute("productos", producto);
        return "productos/listProducto";
    }

    @GetMapping("create")
    public String crear(Producto producto) {

        return "productos/formProducto";
    }

    @PostMapping("save")
    public String guardar(Producto producto, BindingResult result, RedirectAttributes attributes,
            @RequestParam("archivoImagen") MultipartFile multiPart) {
        if (!multiPart.isEmpty()) {
            // String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            // String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                producto.setImagen(nombreImagen);
            }
        }
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "categorias/formProducto";
        } else {
            producto.setStatus(1);
            producto.setFechaRegistro(new Date());
            serviProducto.guardar(producto);
            attributes.addFlashAttribute("msg", "Registro Exitoso");
            return "redirect:/productos/index";
        }
    }

    @GetMapping("edit/{id}")
    public String editar(@PathVariable("id") Integer idProducto, Model model) throws Exception {
        Producto producto = serviProducto.buscarPorId(idProducto);
        model.addAttribute("producto", producto);
        return "productos/formProducto";
    }

    @GetMapping("delete/{id}")
    public String eliminar(@PathVariable("id") Integer idProducto) {
        serviProducto.eliminar(idProducto);
        return "redirect:/productos/index";
    }

    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") int idProducto, RedirectAttributes attributes) {
        serviProducto.activar(idProducto);
        attributes.addFlashAttribute("msg", "El producto fue activado y ahora tiene acceso al sistema.");
        return "redirect:/productos/index";
    }

    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") int idProducto, RedirectAttributes attributes) {
        serviProducto.bloquear(idProducto);
        attributes.addFlashAttribute("msg", "El producto fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/productos/index";
    }

    @ModelAttribute // esta es otra forma de agregar datos al modelo datos que son genericos o
    // comunes en este controlador
    public void getGenerico(Model model, Pageable page) {
        model.addAttribute("categorias", serviCategoria.listarCategoria(page));
    }
}
