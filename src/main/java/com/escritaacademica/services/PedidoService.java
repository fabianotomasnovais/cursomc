package com.escritaacademica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escritaacademica.domain.Pedido;
import com.escritaacademica.repositories.PedidoRepository;
import com.escritaacademica.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo; //Repository of categories

	public Pedido buscar(Integer id) { //Try to find object by id
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); //If not found throw a exception
	}

}
