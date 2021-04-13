package br.com.zup.proposta.cartao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.proposta.integracaocartao.BloqueioRequest;
import br.com.zup.proposta.integracaocartao.BloqueioResponse;
import br.com.zup.proposta.integracaocartao.CartaoCliente;
import feign.FeignException;

@RestController
public class BloqueioController {

	private EntityManager manager;
	private CartaoCliente cartaoClient;
	
	@Autowired
	public BloqueioController(EntityManager manager, CartaoCliente cartaoClient) {
		this.manager = manager;
		this.cartaoClient = cartaoClient;
		
	}
	
	@Transactional
	@PostMapping("/api/cartoes/{idCartao}/bloquear")
	public ResponseEntity<Void> bloquearCartao(@PathVariable ("idCartao") Long idCartao, HttpServletRequest inforRequest, @RequestHeader("user-agent") String userAgent) {
		
		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, idCartao)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do cartão não encontrado"));
		
		if(cartao.bloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		String numCartao = cartao.getNumeroCartao();
		System.out.println(cartao.getNumeroCartao());
		BloqueioResponse response;
		System.out.println(userAgent);
		
		try {
			response = cartaoClient.bloqueiaCartao(numCartao, new BloqueioRequest("Proposta"));
			
		} catch (FeignException.UnprocessableEntity e) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		String ipCliente = inforRequest.getRemoteAddr();
		
		Bloqueio bloqueio = response.toModel(cartao, ipCliente, userAgent);
		
		manager.persist(bloqueio);
		
		return ResponseEntity.ok().build();	
	}
}
