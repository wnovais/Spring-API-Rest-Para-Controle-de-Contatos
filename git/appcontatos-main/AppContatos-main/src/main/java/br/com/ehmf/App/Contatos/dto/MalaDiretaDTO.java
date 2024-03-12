package br.com.ehmf.App.Contatos.dto;

import br.com.ehmf.App.Contatos.model.Pessoa;

public class MalaDiretaDTO {
	
	private Long id;
	private String nome;
	private String enderecoCompleto;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the enderecoCompleto
	 */
	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}
	public MalaDiretaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.enderecoCompleto = pessoa.getEndereco() + " - " 
				+ pessoa.getCep() + " - " + pessoa.getCidade() + "/" + pessoa.getUf();
	}

	/**
	 * @param enderecoCompleto the enderecoCompleto to set
	 */
	public void setEnderecoCompleto(String endereco, String cep, String cidade, String uf) {
		this.enderecoCompleto = endereco + " - " + cep + " - " + cidade + "/" + uf;
	}
}
