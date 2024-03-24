package org.jonlima.iScheduler.security;

import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException{

        Users users = userRepository.findByEmail(usernameOrEmail);
        if(users != null){
            return new org.springframework.security.core.userdetails.User(users.getEmail(),
                    users.getPassword(),
                    users.getRoles().stream()
                            .map((role)-> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
