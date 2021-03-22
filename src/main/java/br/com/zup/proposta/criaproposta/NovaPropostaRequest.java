package br.com.zup.proposta.criaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class NovaPropostaRequest {
	
	@NotBlank @CPFouCNPJ
	private String documento;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotNull @Positive
	private BigDecimal salario;
	@NotNull
	private EnderecoRequest endereco;

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getNome() {
		return nome;
	}

	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco.toModel(), this.salario);
	}
	
	public boolean clientePoussuiProposta(PropostaRepository propostaRepository) {
		return propostaRepository.existsByDocumento(this.documento);
	}
}
