package br.com.zup.proposta.cartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bloqueio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Cartao cartao;
	
	private LocalDateTime bloqueadoEm;
	
	private String ipCliente;
	
	private String userAgent;
	
	@Deprecated
	public Bloqueio() {
		
	}

	public Bloqueio(Cartao cartao, LocalDateTime bloqueadoEm, String ipCliente, String userAgent) {
		this.cartao = cartao;
		this.bloqueadoEm = bloqueadoEm;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}
	
	
}
