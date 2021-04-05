package br.com.zup.proposta.integracaocartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.Proposta;

public class CartaoResponse {

	@JsonProperty("id")
	private String numeroCartao;
	
	private LocalDateTime emitidoEm;
	
	private String titular;
	
	private BigDecimal limite;
	
	private String idProposta;
	
	
	public Cartao toModel(Proposta proposta) {	
		return new Cartao(this.numeroCartao, this.emitidoEm, this.titular, this.limite, proposta);
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
}
