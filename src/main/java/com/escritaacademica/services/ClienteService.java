package com.escritaacademica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escritaacademica.domain.Cliente;
import com.escritaacademica.repositories.ClienteRepository;
import com.escritaacademica.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo; //Repository of categories

	public Cliente buscar(Integer id) { //Try to find object by id
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); //If not found throw a exception
	}

}
