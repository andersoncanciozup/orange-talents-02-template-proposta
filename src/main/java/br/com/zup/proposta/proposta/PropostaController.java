package br.com.zup.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.integracaoanalise.AnaliseClient;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private AnaliseClient analiseClient;

	@Transactional
	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uri) {

		if (request.clientePoussuiProposta(propostaRepository)) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Proposta novaProposta = request.toModel();
		propostaRepository.save(novaProposta);

		novaProposta.analisaProposta(analiseClient);

		URI location = uri.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaProposta(@PathVariable Long id) {
		
		Optional<Proposta> propostaConsultada = propostaRepository.findById(id);
		
		if(propostaConsultada.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new PropostaResponse(propostaConsultada.get()));
	}
}