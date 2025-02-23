package com.example.demo.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//tiene que tener 3 cosas paswordRncoder, AutenticationManager, Servicio de usuarios (UserDetailsService)
public class SecurityConfig {

	@Autowired private UserDetailsService userDetailsService;
	
	@Bean
     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/user/delete").hasRole("ADMIN")
                    .requestMatchers("/nave/**").authenticated()
                    .requestMatchers("/participa/**").authenticated()
                    .anyRequest().permitAll()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/")
                    .failureUrl("/login")
            )
            .logout(logout -> 
            	logout
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
	@Bean
	//tengo que decirle cual es el codificador que voy a usar 
	//y tambien que servicio de mi proyecto se va a encargar de decirle de donde recoge los usuarios
	//como los recoge... en este caso se llama UserDetailsService
	  AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}
	
	//sistema con el que estan encritpadas nuestras contraseñas
	@Bean
	 PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
}
