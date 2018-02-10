package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.repositories.CategoriaRepository;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Categoria categoria = repo.findOne(id);
        if(categoria == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Categoria.class.getName()  );
        }
        return categoria;
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }
}
