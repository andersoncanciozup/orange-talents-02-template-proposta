package br.com.zup.proposta.criaproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.proposta.integracao.AnaliseClient;
import br.com.zup.proposta.integracao.AnaliseReponse;
import br.com.zup.proposta.integracao.AnaliseRequest;
import feign.FeignException;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@Embedded
	private Endereco endereco;

	@Column(nullable = false)
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_proposta", nullable = false)
	private StatusProposta status = StatusProposta.NAO_ELEGIVEL;
	
	private String numeroCartao;

	@Deprecated
	public Proposta() {

	}

	public Proposta(String documento, String email, String nome, Endereco endereco, BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}
	
	public StatusProposta getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Proposta { documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco=" + endereco
				+ ", salario=" + salario + ", status=" + status + "}";
	}

	public void analisaProposta(AnaliseClient analiseClient) {
		AnaliseRequest analiseRequest = new AnaliseRequest(this);
		
		try {
			AnaliseReponse response = analiseClient.consultaAnalise(analiseRequest);
			this.status = response.paraStatus();
		} catch (FeignException.UnprocessableEntity e) {
			this.status = StatusProposta.NAO_ELEGIVEL;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado");
		}
	}

	public void associarCartao() {
		
		
		
		
		
	}
}
