package br.com.zup.proposta.biometria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Biometria {
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cartao cartao;
	
	@Column(name = "impressao_digital", nullable = false)
	private String impressaoDigital;
	
	@Deprecated
	public Biometria() {
		
	}
		
	public Biometria(Cartao cartao, @NotBlank String impressaoDigital) {
		this.cartao = cartao;
		this.impressaoDigital = impressaoDigital;
	}

	public Long getId() {
		return id;
	}

}
