package br.com.gmcb.restaurantfinder.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer

@Profile("disable")
public class DisableSecurityConfiguration extends ResourceServerConfigurerAdapter {


    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
       http.csrf().disable()
       .authorizeRequests().antMatchers("/restaurants/**").access("hasRole('ADMIN')").and()
       .authorizeRequests().antMatchers("/**").permitAll().
       and().authorizeRequests().antMatchers("/console/**").permitAll().antMatchers("/restaurants").authenticated();

       http.headers().frameOptions().disable();
        // @formatter:on
    }
}
