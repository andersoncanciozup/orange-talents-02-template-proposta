package br.com.zup.proposta.integracaoanalise;

import br.com.zup.proposta.proposta.StatusProposta;

public class AnaliseReponse {

	private String resultadoSolicitacao;
	private String idProposta;
	
	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "AnaliseReponse [resultadoSolicitacao=" + resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}

	public StatusProposta paraStatus() {
		if("COM_RESTRICAO".equals(this.resultadoSolicitacao)) {
			return StatusProposta.NAO_ELEGIVEL;
		}
		return StatusProposta.ELEGIVEL;
	}
		
}
