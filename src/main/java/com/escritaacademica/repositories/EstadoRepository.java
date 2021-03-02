package com.escritaacademica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escritaacademica.domain.Estado;

//Realiza operações de acesso a dados no banco de dados (buscar, deletar, adicionar,editar)
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
