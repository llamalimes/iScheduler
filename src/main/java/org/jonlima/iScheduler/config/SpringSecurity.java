package org.jonlima.iScheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

    @Configuration
    @EnableWebSecurity
    public class SpringSecurity {
        @Bean
        public static PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
            //http.csrf().disable()   DEPRECATED WAY OF DOING THINGS
            http.csrf((csrf) -> csrf.disable()) //Ezra's Fix
                    //.authorizeHttpRequests()  DEPRECATED WAY OF DOING THINGS
                    .authorizeHttpRequests((authz) -> authz //Ezra's fix
                                    .requestMatchers("/register/**").permitAll()
                                    .requestMatchers("/index").permitAll()
                                    .requestMatchers("/users").hasRole("ADMIN")
                            //.and()  DEPRECATED WAY OF DOING THINGS
                    ) //Ezra's fix
                    .formLogin(
                            form -> form
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .defaultSuccessUrl("/users")
                                    .permitAll()
                    ).logout(
                            logout -> logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll());
            return http.build();

        }
    }
