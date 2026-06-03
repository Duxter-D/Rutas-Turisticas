package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import rutasturisticas.api.aplicacion.servicios.TipoServicio;
import rutasturisticas.api.dominio.entidades.Tipo;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "tipo-controlador")
@CrossOrigin(origins = "*")
public class TipoControlador {

    @Autowired
    private TipoServicio servicio;

    @GetMapping("/")
    public ResponseEntity<List<Tipo>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> get(@PathVariable int id) {
        Tipo tipo = servicio.get(id);
        if (tipo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tipo);
    }

    @PostMapping("/")
    public ResponseEntity<Tipo> agregar(@RequestBody Tipo tipo) {
        Tipo creado = servicio.agregar(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/")
    public ResponseEntity<Tipo> modificar(@RequestBody Tipo tipo) {
        Tipo actualizado = servicio.modificar(tipo);
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
