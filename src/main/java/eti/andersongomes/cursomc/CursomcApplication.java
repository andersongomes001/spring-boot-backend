package eti.andersongomes.cursomc;

import eti.andersongomes.cursomc.domain.*;
import eti.andersongomes.cursomc.domain.enums.EstadoPagamento;
import eti.andersongomes.cursomc.domain.enums.TipoCliente;
import eti.andersongomes.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
    @Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

        Produto p1 = new Produto(null,"Computador", 2000.00);
        Produto p2 = new Produto(null,"Impressora", 800.00);
        Produto p3 = new Produto(null,"Mouse", 80.00);

        cat1.getProdutos().addAll( Arrays.asList(p1,p2,p3) );
        cat2.getProdutos().add(p2);// .addAll( Arrays.asList(p2) );

        p1.getCategorias().add(cat1);//.addAll( Arrays.asList(cat1) );
        p2.getCategorias().addAll( Arrays.asList(cat1,cat2) );
        p3.getCategorias().add(cat1);//.addAll( Arrays.asList(cat1) );

		categoriaRepository.save( Arrays.asList(cat1,cat2) );
        produtoRepository.save(Arrays.asList(p1,p2,p3));



		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));

        Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "08079244073", TipoCliente.PESSOAFISICA);
        cl1.getTelefones().addAll(Arrays.asList("123456789","987654321"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 203", "jardim", "38220834", cl1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38777012", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(e1,e2));

        clienteRepository.save(Arrays.asList(cl1));
        enderecoRepository.save(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy hh:mm");
        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cl1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1,e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));


	}
}
