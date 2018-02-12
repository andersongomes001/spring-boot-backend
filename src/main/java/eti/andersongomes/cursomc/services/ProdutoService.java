package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.domain.Pedido;
import eti.andersongomes.cursomc.domain.Produto;
import eti.andersongomes.cursomc.repositories.CategoriaRepository;
import eti.andersongomes.cursomc.repositories.PedidoRepository;
import eti.andersongomes.cursomc.repositories.ProdutoRepository;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
        Produto produto = repo.findOne(id);
        if(produto == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Produto.class.getName()  );
        }
        return produto;
    }

    public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAll(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
