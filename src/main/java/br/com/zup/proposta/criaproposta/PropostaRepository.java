package br.com.zup.proposta.criaproposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Boolean existsByDocumento(String documento);
	
}
