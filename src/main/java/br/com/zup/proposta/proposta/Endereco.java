package br.com.zup.proposta.proposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cep;
	
	@Deprecated
	public Endereco() {
		
	}
	
	public Endereco(@NotBlank String logradouro, @NotBlank String numero, @NotBlank String bairro,
			@NotBlank String cep) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
	}	
}
