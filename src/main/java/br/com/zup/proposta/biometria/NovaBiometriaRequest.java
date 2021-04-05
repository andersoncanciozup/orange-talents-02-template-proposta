package br.com.zup.proposta.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.cartao.Cartao;

public class NovaBiometriaRequest {

	@NotBlank
	private String impressaoDigital;
	
	//Set para desserializar o json
	public void setImpressaoDigital(String impressaoDigital) {
		this.impressaoDigital = Base64.getEncoder().encodeToString(impressaoDigital.getBytes());;
	}
	
	public String getBiometria() {
		return impressaoDigital;
	}	
	
	public Biometria toModel(Cartao cartao) {
		return new Biometria(cartao, this.impressaoDigital);
	}
	
}
