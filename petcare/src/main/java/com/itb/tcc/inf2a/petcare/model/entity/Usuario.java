package com.itb.tcc.inf2a.petcare.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.itb.tcc.inf2a.petcare.model.enums.EnumTipoUsuario;

@Entity
@Table(name="usuario")

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private LocalDateTime data_nascimento;
	private String genero;
	private String senha;
	private boolean codStatusUsuario;	
	
	@Enumerated(EnumType.STRING)
	private EnumTipoUsuario tipoUsuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public LocalDateTime getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDateTime data_nascimento) {
		this.data_nascimento = data_nascimento;
	} 

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isCodStatusUsuario() {
		return codStatusUsuario;
	}

	public void setCodStatusUsuario(boolean codStatusUsuario) {
		this.codStatusUsuario = codStatusUsuario;
	}
	
	public EnumTipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(EnumTipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
