package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Categoria categoria = repo.findOne(id);
        return categoria;
    }
}
