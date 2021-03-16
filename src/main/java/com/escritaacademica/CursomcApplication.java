package com.escritaacademica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.escritaacademica.domain.ItemPedido;
import com.escritaacademica.domain.Pagamento;
import com.escritaacademica.domain.PagamentoComBoleto;
import com.escritaacademica.domain.PagamentoComCartao;
import com.escritaacademica.domain.Pedido;
import com.escritaacademica.domain.Produto;
import com.escritaacademica.domain.enums.EstadoPagamento;
import com.escritaacademica.domain.enums.TipoCliente;
import com.escritaacademica.repositories.CategoriaRepository;
import com.escritaacademica.repositories.CidadeRepository;
import com.escritaacademica.repositories.ClienteRepository;
import com.escritaacademica.repositories.EnderecoRepository;
import com.escritaacademica.repositories.EstadoRepository;
import com.escritaacademica.repositories.ItemPedidoRepository;
import com.escritaacademica.repositories.PagamentoRepository;
import com.escritaacademica.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		//Create states
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		//Create cities
		Cidade c1 = new Cidade(null,"Ouro Preto",est1);
		Cidade c2 = new Cidade(null,"Cachoeira do Campo 2",est1);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));	
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		//Create clients
		Cliente cli1 = new Cliente(null,"Aline Silva","linesialho@gmail.com","04508057085",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("35515555","975277682"));
		
		//Create address
		Endereco e1 = new Endereco(null, "Rua das Flores", "20", "Apt 304", "Centro", "35410000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Rezende", "45", "Casa", "Antônio Dias", "35400000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		//Create orders
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		//Create payment
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2)); //Salvar pedido antes de pagamento
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);


		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		

		
		
	}
}
