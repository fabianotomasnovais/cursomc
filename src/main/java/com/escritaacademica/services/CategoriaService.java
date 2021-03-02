package com.escritaacademica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escritaacademica.domain.Categoria;
import com.escritaacademica.repositories.CategoriaRepository;
import com.escritaacademica.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo; //Repository of categories

	public Categoria buscar(Integer id) { //Try to find object by id
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); //If not found throw a exception
	}

}
