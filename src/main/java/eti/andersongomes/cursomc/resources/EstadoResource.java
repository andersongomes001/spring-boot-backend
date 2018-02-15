package eti.andersongomes.cursomc.resources;

import eti.andersongomes.cursomc.domain.Cidade;
import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Estado;
import eti.andersongomes.cursomc.dto.CidadeDTo;
import eti.andersongomes.cursomc.dto.ClienteDTO;
import eti.andersongomes.cursomc.dto.ClienteNewDTO;
import eti.andersongomes.cursomc.dto.EstadoDTO;
import eti.andersongomes.cursomc.services.CidadeService;
import eti.andersongomes.cursomc.services.ClienteService;
import eti.andersongomes.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> find(){
        List<Estado> estado = estadoService.findAll();
        List<EstadoDTO> estadoDTOS = estado
                .stream()
                .map(x -> new EstadoDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(estadoDTOS);
    }
    @RequestMapping(value = "{estadoId}/cidades",method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTo>> findCidades(@PathVariable Integer estadoId){
        List<Cidade> cidades = cidadeService.findByEstado(estadoId);
        List<CidadeDTo> cidadeDTos = cidades
                .stream()
                .map(x -> new CidadeDTo(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(cidadeDTos);
    }
}
