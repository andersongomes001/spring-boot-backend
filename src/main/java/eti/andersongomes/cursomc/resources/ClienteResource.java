package eti.andersongomes.cursomc.resources;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.dto.CategoriaDTO;
import eti.andersongomes.cursomc.dto.ClienteDTO;
import eti.andersongomes.cursomc.dto.ClienteNewDTO;
import eti.andersongomes.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cliente = service.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@Valid @RequestBody ClienteNewDTO clienteDTO){
        Cliente cliente = service.fronDTO(clienteDTO);
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = service.fronDTO(clienteDTO);
        cliente.setId(id);
        service.update(cliente);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list
                .stream()
                .map(
                        obj -> new ClienteDTO(obj)
                ).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Cliente> list =service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
