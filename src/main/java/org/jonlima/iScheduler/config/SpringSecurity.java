package org.jonlima.iScheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
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
        //@Bean This was breaking the authentication process somehow
        @Autowired //Ezra's fix
        public static PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
            //http.csrf().disable()   DEPRECATED WAY OF DOING THINGS
            http.csrf((csrf) -> csrf.disable()) //Ezra's Fix
                    //.authorizeHttpRequests()  DEPRECATED WAY OF DOING THINGS
                    .authorizeHttpRequests((authz) -> authz //Ezra's fix
                                    .requestMatchers("/").permitAll()
                                    .requestMatchers("/js/**").permitAll()
                                    .requestMatchers("/register/**").permitAll()
                                    .requestMatchers("/index").permitAll()
                                    .requestMatchers("/users").hasRole("ADMIN")
                                    .requestMatchers("/add-friend").hasRole("ADMIN")
                                    .requestMatchers("/remove-friend").hasRole("ADMIN")
                                    .requestMatchers("/availability/**").hasRole("ADMIN")
                                    .requestMatchers("/compare-availabilities*").hasRole("ADMIN")
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

