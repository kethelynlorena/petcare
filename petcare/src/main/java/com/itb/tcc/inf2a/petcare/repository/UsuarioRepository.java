package com.itb.tcc.inf2a.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itb.tcc.inf2a.petcare.model.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 @Query(value = "select * from clientes where email = :email and senha = :senha", nativeQuery = true)
	    public Usuario acessar(String email, String senha);

}