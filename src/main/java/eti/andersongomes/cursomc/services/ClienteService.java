package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.repositories.ClienteRepository;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id){
        Cliente cliente = repo.findOne(id);
        if(cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Cliente.class.getName()  );
        }
        return cliente;
    }
}
