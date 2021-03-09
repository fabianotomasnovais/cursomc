package com.escritaacademica.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.escritaacademica.domain.Cliente;
import com.escritaacademica.services.ClienteService;

//Rest Controller to response the client requests 

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired //Instanciar automaticamente
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET) //Use the id to find the object
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.buscar(id); //Service try to find the object
		return ResponseEntity.ok().body(obj); //Return the object
	}
				
}
