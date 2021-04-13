package br.com.zup.proposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests(authorizeRequests ->
	                        authorizeRequests
	                                .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_proposal:read")
	                                .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_card:read")
	                                .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_card:write")
	                                .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_proposal:write")
	                                .anyRequest().authenticated()
	                )
	                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	    
	}

}
