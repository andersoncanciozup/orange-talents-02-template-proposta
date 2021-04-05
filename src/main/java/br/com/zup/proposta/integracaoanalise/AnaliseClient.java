package br.com.zup.proposta.integracaoanalise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseClient", url = "${analiseClient.targetUrl}")
public interface AnaliseClient {

	@PostMapping("/api/solicitacao")
	public AnaliseReponse consultaAnalise(@RequestBody AnaliseRequest request);
	
}
