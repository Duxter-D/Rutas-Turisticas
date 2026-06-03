package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import rutasturisticas.api.aplicacion.servicios.RutaServicio;
import rutasturisticas.api.dominio.entidades.Ruta;

@RestController
@RequestMapping("/api/rutas")
@Tag(name = "ruta-controlador")
@CrossOrigin(origins = "*")
public class RutaControlador {

    @Autowired
    private RutaServicio servicio;

    @GetMapping("/")
    public ResponseEntity<List<Ruta>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> get(@PathVariable int id) {
        Ruta ruta = servicio.get(id);
        if (ruta == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ruta);
    }

    // Endpoint especial: rutas de una ciudad
    @GetMapping("/ciudad/{idCiudad}")
    public ResponseEntity<List<Ruta>> listarPorCiudad(@PathVariable int idCiudad) {
        return ResponseEntity.ok(servicio.listarCiudad(idCiudad));
    }

    @PostMapping("/")
    public ResponseEntity<Ruta> agregar(@RequestBody Ruta ruta) {
        Ruta creada = servicio.agregar(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/")
    public ResponseEntity<Ruta> modificar(@RequestBody Ruta ruta) {
        Ruta actualizada = servicio.modificar(ruta);
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
