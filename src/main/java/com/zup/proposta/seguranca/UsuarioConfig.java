package com.zup.proposta.seguranca;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/proposta/**").hasRole("SCOPE_zup:read")
                .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasRole("SCOPE_zup:read")
                .antMatchers(HttpMethod.POST, "/api/proposta/**").hasRole("SCOPE_zup:write")
                .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasRole("SCOPE_zup:write")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
