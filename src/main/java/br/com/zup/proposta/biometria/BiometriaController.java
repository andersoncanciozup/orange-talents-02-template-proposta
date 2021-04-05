package br.com.zup.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;

@RestController
@RequestMapping("/api/cartoes/{idCartao}/biometria")
public class BiometriaController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaBiometria(@PathVariable("idCartao") Long idCartao,
			@RequestBody @Valid NovaBiometriaRequest request) {

		Optional<Cartao> cartaoConsultado = Optional.ofNullable(manager.find(Cartao.class, idCartao));

		if (cartaoConsultado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Biometria novaBiometria = cartaoConsultado.map(cartao -> request.toModel(cartao)).get();

		manager.persist(novaBiometria);

		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().pathSegment("api/biometrics/{id}")
				.buildAndExpand(novaBiometria.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
}
