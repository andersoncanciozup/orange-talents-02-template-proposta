package br.com.zup.proposta.analise;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.proposta.proposta.EnderecoRequest;
import br.com.zup.proposta.proposta.NovaPropostaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AnaliseTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void deveCriarNovaPropostaComSucesso() throws JsonProcessingException, Exception {

		EnderecoRequest endereco = new EnderecoRequest("Av. Oscar", "840", "compensa", "00000-000");
		NovaPropostaRequest propostaRequest = new NovaPropostaRequest("015.976.912-40", "usuario@usuario.com.br",
				"Fulano Oliveira", new BigDecimal(1200), endereco);

		mockMvc.perform(post("/api/propostas").contentType("application/json")
				.content(mapper.writeValueAsString(propostaRequest))).andExpect(status().isCreated());

	}

	@Test
	@DisplayName("não deve aceitar mais de uma proposta com o mesmo documento, retornando um Unprocessable Entity")
	public void naoDeveCriarPropostaComDocumentoJaExistente() throws JsonProcessingException, Exception {
				
		EnderecoRequest endereco = new EnderecoRequest("Av. Oscar", "840", "compensa", "00000-000");
		NovaPropostaRequest request = new NovaPropostaRequest("536.008.450-20", "usuario@usuario.com.br",
				"Fulano Oliveira", new BigDecimal(1200), endereco);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
				.content(mapper.writeValueAsString(request))).andExpect(status().isCreated());
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
				.content(mapper.writeValueAsString(request))).andExpect(status().isUnprocessableEntity());

	}

}
