package com.escritaacademica.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.escritaacademica.domain.Pedido;
import com.escritaacademica.services.PedidoService;

//Rest Controller to response the client requests 

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired //Instanciar automaticamente
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET) //Use the id to find the object
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido obj = service.buscar(id); //Service try to find the object
		return ResponseEntity.ok().body(obj); //Return the object
	}
				
}
