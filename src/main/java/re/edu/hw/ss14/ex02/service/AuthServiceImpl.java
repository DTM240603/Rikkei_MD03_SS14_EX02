package re.edu.hw.ss14.ex02.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import re.edu.hw.ss14.ex02.config.jwt.JwtProvider;
import re.edu.hw.ss14.ex02.dto.request.FormLogin;
import re.edu.hw.ss14.ex02.dto.request.FormRegister;
import re.edu.hw.ss14.ex02.dto.response.JwtResponse;
import re.edu.hw.ss14.ex02.entity.User;
import re.edu.hw.ss14.ex02.repository.IUserRepository;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(FormLogin request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails);

        return new JwtResponse(token, "Bearer", userDetails.getUsername());
    }

    @Override
    @Transactional
    public String register(FormRegister request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(normalizeRole(request.getRole()));
        userRepository.save(user);

        return "Register successful: " + user.getUsername();
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return "USER";
        }
        return role.replaceFirst("^ROLE_", "").toUpperCase(Locale.ROOT);
    }
}
