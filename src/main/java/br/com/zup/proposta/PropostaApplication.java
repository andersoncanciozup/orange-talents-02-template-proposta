package br.com.zup.proposta;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
		
		String mensagem = "Testando Base64";
		String mensagemCodificada = Base64.getEncoder().encodeToString(mensagem.getBytes());
		System.out.println(mensagemCodificada);
	}

}
