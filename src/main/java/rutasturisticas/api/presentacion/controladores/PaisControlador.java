package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import rutasturisticas.api.aplicacion.servicios.PaisServicio;
import rutasturisticas.api.dominio.entidades.Pais;

@RestController
@RequestMapping("/api/paises")
@Tag(name = "pais-controlador")
@CrossOrigin(origins = "*")
public class PaisControlador {

    @Autowired
    private PaisServicio servicio;

    @GetMapping("/")
    public ResponseEntity<List<Pais>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> get(@PathVariable int id) {
        Pais pais = servicio.get(id);
        if (pais == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pais);
    }

    @PostMapping("/")
    public ResponseEntity<Pais> agregar(@RequestBody Pais pais) {
        Pais creado = servicio.agregar(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/")
    public ResponseEntity<Pais> modificar(@RequestBody Pais pais) {
        Pais actualizado = servicio.modificar(pais);
        if (actualizado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!servicio.eliminar(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
