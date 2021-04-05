package br.com.zup.proposta.cartao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.integracaocartao.CartaoCliente;
import br.com.zup.proposta.integracaocartao.CartaoResponse;
import br.com.zup.proposta.integracaocartao.NovoCartaoRequest;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import br.com.zup.proposta.proposta.StatusProposta;

@Component
public class AdicionaCartao {
	
	    @Autowired
	    private CartaoCliente cartaoClient;
	    
	    @Autowired
	    private PropostaRepository propostaRepository;

	    @Transactional
	    @Scheduled(fixedDelay = 10000)
	    public void criaCartao() {
	        List<Proposta> listaPropostaSemCartao = propostaRepository.findAllByStatusAndCartaoIsNull(StatusProposta.ELEGIVEL);

	        for (Proposta proposta : listaPropostaSemCartao) {
	        	CartaoResponse response = cartaoClient.adicionaCartao(new NovoCartaoRequest(proposta));
	        	Cartao cartao = response.toModel(proposta);
	        	proposta.adicionaCartao(cartao);
	        	propostaRepository.save(proposta);
			}
	        
	    }

}