package br.com.zup.proposta.criaproposta;

public class PropostaResponse {

	private String nome;
	private String documento;
	private StatusProposta status;
	
	public PropostaResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
		this.status = proposta.getStatus();
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public StatusProposta getStatus() {
		return status;
	}

	
	
}
