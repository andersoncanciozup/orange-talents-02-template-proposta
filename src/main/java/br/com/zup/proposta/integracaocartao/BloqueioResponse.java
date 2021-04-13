package br.com.zup.proposta.integracaocartao;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import br.com.zup.proposta.cartao.Bloqueio;
import br.com.zup.proposta.cartao.Cartao;

public class BloqueioResponse {

	    private LocalDateTime bloqueadoEm = LocalDateTime.now();
	    
	    private String resultado;

	    public Bloqueio toModel(Cartao cartao, String ipCliente, String userAgent) {
//	    	Assert.isNull(resultado, "veio null");
//	    	Assert.isTrue(resultado.equals("BLOQUEADO"), "Falha durante o processamento");
	        return new Bloqueio(cartao, bloqueadoEm, ipCliente, userAgent);
	    }

}
