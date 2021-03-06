package com.escritaacademica.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.escritaacademica.domain.Categoria;
import com.escritaacademica.services.CategoriaService;

//Rest Controller to response the client requests 

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired //Instanciar automaticamente
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET) //Use the id to find the object
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id); //Service try to find the object
		return ResponseEntity.ok().body(obj); //Return the object
	}
				
}
