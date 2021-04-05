package br.com.zup.proposta.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cep;

	public EnderecoRequest(@NotBlank String logradouro, @NotBlank String numero, @NotBlank String bairro,
			@NotBlank String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}
	
	public Endereco toModel() {
		return new Endereco(this.logradouro, this.numero, this.bairro, this.cep);
	}
}
