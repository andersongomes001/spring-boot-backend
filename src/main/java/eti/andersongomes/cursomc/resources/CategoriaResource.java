package eti.andersongomes.cursomc.resources;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.dto.CategoriaDTO;
import eti.andersongomes.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@Valid @RequestBody CategoriaDTO categoriaDto){
        Categoria categoria = service.fronDTO(categoriaDto);
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
        Categoria categoria = service.fronDTO(categoriaDTO);
        categoria.setId(id);
        categoria = service.update(categoria);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list
                .stream()
                .map(
                        obj -> new CategoriaDTO(obj)
                ).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Categoria> list =service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
