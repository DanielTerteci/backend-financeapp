package com.finance.app.service.impl;

import com.finance.app.models.CustomUserDetails;
import com.finance.app.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RegisterRepository registerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = registerRepository.findByUsername(username);
        return user.map(CustomUserDetails::new).orElse(null);
    }
}
