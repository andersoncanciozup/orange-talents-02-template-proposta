package br.com.zup.proposta.seguranca;

import com.auth0.jwt.JWT;

public class JwtDecoder {
	
	public static String getEmail(String token) {
		return JWT.decode(token.substring(7)).getClaim("email").asString();
	}
}
