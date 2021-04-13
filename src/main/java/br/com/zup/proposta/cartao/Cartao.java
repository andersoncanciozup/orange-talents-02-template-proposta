package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.seguranca.JwtDecoder;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numeroCartao;

	private LocalDateTime emitidoEm;

	private String titular;

	private BigDecimal limite;

	@OneToOne
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometria;

	@OneToOne(mappedBy = "cartao")
	private Bloqueio bloqueio;

	@Deprecated
	public Cartao() {
		super();
	}

	public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	@Override
	public String toString() {
		return "Cartao [numeroCartao=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", titular=" + titular
				+ ", limite=" + limite + ", proposta=" + proposta + "]";
	}

	public boolean pertenceAoUsuario(String token) {
		Assert.isTrue(StringUtils.hasText(token), "token não pode estar em branco");
		System.out.println(JwtDecoder.getEmail(token));
		return this.proposta.getEmail().equals(JwtDecoder.getEmail(token));
	}

	public boolean bloqueado() {
		if (this.bloqueio == null) {
			return false;
		}
		return true;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

}
