package br.com.zup.proposta.integracaoanalise;

import br.com.zup.proposta.proposta.Proposta;

public class AnaliseRequest {

	private String documento;
	
	private String nome;
	
	private String idProposta;

	public AnaliseRequest(Proposta novaProposta) {
		this.documento = novaProposta.getDocumento();
		this.nome = novaProposta.getNome();
		this.idProposta = novaProposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "AnaliseRequest [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta + "]";
	}
	
}
