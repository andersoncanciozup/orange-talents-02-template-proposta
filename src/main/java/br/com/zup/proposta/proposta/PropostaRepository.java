package br.com.zup.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Boolean existsByDocumento(String documento);
	
	Optional<Proposta> findFirstByStatusAndCartaoIsNull(StatusProposta elegivel);

	List<Proposta> findAllByStatusAndCartaoIsNull(StatusProposta elegivel);
}
