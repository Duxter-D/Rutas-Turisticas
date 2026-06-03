package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import rutasturisticas.api.aplicacion.servicios.CiudadServicio;
import rutasturisticas.api.dominio.entidades.Ciudad;

@RestController
@RequestMapping("/api/ciudades")
@Tag(name = "ciudad-controlador")
@CrossOrigin(origins = "*")
public class CiudadControlador {

    @Autowired
    private CiudadServicio servicio;

    @GetMapping("/")
    public ResponseEntity<List<Ciudad>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> get(@PathVariable int id) {
        Ciudad ciudad = servicio.get(id);
        if (ciudad == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ciudad);
    }

    @PostMapping("/")
    public ResponseEntity<Ciudad> agregar(@RequestBody Ciudad ciudad) {
        Ciudad creada = servicio.agregar(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/")
    public ResponseEntity<Ciudad> modificar(@RequestBody Ciudad ciudad) {
        Ciudad actualizada = servicio.modificar(ciudad);
        if (actualizada == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!servicio.eliminar(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
