package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Estado;
import eti.andersongomes.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll(){
        return estadoRepository.findAllByOrderByNome();
    }
}
