package re.edu.hw.ss14.ex02.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import re.edu.hw.ss14.ex02.entity.User;
import re.edu.hw.ss14.ex02.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String keyword) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(keyword, keyword)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + keyword));

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
