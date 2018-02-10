package eti.andersongomes.cursomc.resources;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Categoria categoria = service.buscar(id);
        return ResponseEntity.ok().body(categoria);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Categoria categoria){
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
