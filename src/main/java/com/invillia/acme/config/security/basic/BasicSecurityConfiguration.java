package com.invillia.acme.config.security.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter{

    private static final String ROLE_USER="USER";

    private static final String KEY_PASS="autentication.password";
    private static final String KEY_USER="autentication.user";

    @Autowired
    private Environment env;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser(env.getProperty(KEY_USER)).password(env.getProperty(KEY_PASS)).roles(ROLE_USER);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
