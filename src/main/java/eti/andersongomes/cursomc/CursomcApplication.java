package eti.andersongomes.cursomc;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.domain.Cidade;
import eti.andersongomes.cursomc.domain.Estado;
import eti.andersongomes.cursomc.domain.Produto;
import eti.andersongomes.cursomc.repositories.CategoriaRepository;
import eti.andersongomes.cursomc.repositories.CidadeRepository;
import eti.andersongomes.cursomc.repositories.EstadoRepository;
import eti.andersongomes.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	}
}
