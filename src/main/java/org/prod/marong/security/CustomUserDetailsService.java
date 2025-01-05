package org.prod.marong.security;

import org.prod.marong.model.entity.RoleEntity;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByGmail(gmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with gmail: " + gmail);
        }
        return new User(user.getGmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserId(userId);
        return new User(user.getGmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
