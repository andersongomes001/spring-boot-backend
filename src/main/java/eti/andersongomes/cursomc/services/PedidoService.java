package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Pedido;
import eti.andersongomes.cursomc.repositories.ClienteRepository;
import eti.andersongomes.cursomc.repositories.PedidoRepository;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id){
        Pedido pedido = repo.findOne(id);
        if(pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Pedido.class.getName()  );
        }
        return pedido;
    }
}
