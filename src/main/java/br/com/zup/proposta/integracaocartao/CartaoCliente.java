package br.com.zup.proposta.integracaocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartaoClient", url = "${cartaoClient.targetUrl}")
public interface CartaoCliente {
	
	@PostMapping("/api/cartoes")
	public CartaoResponse adicionaCartao(@RequestBody NovoCartaoRequest request);

	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
	public BloqueioResponse bloqueiaCartao(@PathVariable ("id") String id, @RequestBody BloqueioRequest request);
	
}
