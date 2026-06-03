package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import rutasturisticas.api.aplicacion.servicios.ParadaServicio;
import rutasturisticas.api.dominio.entidades.Parada;

@RestController
@RequestMapping("/api/paradas")
@Tag(name = "parada-controlador")
@CrossOrigin(origins = "*")
public class ParadaControlador {

    @Autowired
    private ParadaServicio servicio;

    @GetMapping("/")
    public ResponseEntity<List<Parada>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> get(@PathVariable int id) {
        Parada parada = servicio.get(id);
        if (parada == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(parada);
    }

    // Endpoint especial: paradas de una ruta ordenadas por campo orden
    @GetMapping("/ruta/{idRuta}")
    public ResponseEntity<List<Parada>> listarPorRuta(@PathVariable int idRuta) {
        return ResponseEntity.ok(servicio.listarRuta(idRuta));
    }

    @PostMapping("/")
    public ResponseEntity<Parada> agregar(@RequestBody Parada parada) {
        Parada creada = servicio.agregar(parada);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/")
    public ResponseEntity<Parada> modificar(@RequestBody Parada parada) {
        Parada actualizada = servicio.modificar(parada);
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
