package br.com.zup.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.proposta.compartilhado.CPFouCNPJ;

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

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@Positive BigDecimal salario, EnderecoRequest endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

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
