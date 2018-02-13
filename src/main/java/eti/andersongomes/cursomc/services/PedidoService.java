package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.*;
import eti.andersongomes.cursomc.domain.enums.EstadoPagamento;
import eti.andersongomes.cursomc.repositories.*;
import eti.andersongomes.cursomc.security.UserSS;
import eti.andersongomes.cursomc.services.exceptions.AuthorizationException;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id){
        Pedido pedido = repo.findOne(id);
        if(pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Pedido.class.getName()  );
        }
        return pedido;
    }

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
        if(pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto,pedido.getInstante());
        }
        pedido = repo.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for(ItemPedido ip : pedido.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);

        }
        itemPedidoRepository.save(pedido.getItens());
        emailService.sendOrderConfimationEmail(pedido);
        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS userSS = UserService.authenticated();
        if(userSS == null) throw new AuthorizationException("Acesso negado");

        Cliente cliente = clienteRepository.findOne(userSS.getId());
        PageRequest pageRequest = new PageRequest(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findByCliente(cliente, pageRequest);
    }
}
