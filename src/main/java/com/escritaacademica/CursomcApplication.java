package com.escritaacademica;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.escritaacademica.domain.Categoria;
import com.escritaacademica.domain.Cidade;
import com.escritaacademica.domain.Cliente;
import com.escritaacademica.domain.Endereco;
import com.escritaacademica.domain.Estado;
import com.escritaacademica.domain.Produto;
import com.escritaacademica.domain.enums.TipoCliente;
import com.escritaacademica.repositories.CategoriaRepository;
import com.escritaacademica.repositories.CidadeRepository;
import com.escritaacademica.repositories.ClienteRepository;
import com.escritaacademica.repositories.EnderecoRepository;
import com.escritaacademica.repositories.EstadoRepository;
import com.escritaacademica.repositories.ProdutoRepository;

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// Execute an action on start
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//Create the categories
		Categoria cat1 = new Categoria(null, "Computers");
		Categoria cat2 = new Categoria(null, "Office");
		
		//Create the products
		Produto p1 = new Produto(null, "Computer", 2000.00);
		Produto p2 = new Produto(null, "Printer", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//Make the relationships 
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Ouro Preto",est1);
		Cidade c2 = new Cidade(null,"Cachoeira do Campo 2",est1);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));	
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Aline Silva","linesialho@gmail.com","04508057085",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("35515555","975277682"));
		
		Endereco e1 = new Endereco(null, "Rua das Flores", "20", "Apt 304", "Centro", "35410000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Rezende", "45", "Casa", "Antônio Dias", "35400000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}
}
