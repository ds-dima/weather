package com.dsdima.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * Web security configuration
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Ignoring static resources
     * @param web web
     * @throws Exception exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * Configure in memory users
     * @param auth auth
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("user1").password("password").roles("USER").and()
                .withUser("user2").password("password").roles("USER").and()
                .withUser("user3").password("password").roles("USER").and()
                .withUser("user4").password("password").roles("USER").and()
                .withUser("user5").password("password").roles("USER");
    }

    /**
     * Configure web security rules
     * @param http http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout()
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                .and()
                    .headers().defaultsDisabled()
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .csrf().disable();
    }

}
