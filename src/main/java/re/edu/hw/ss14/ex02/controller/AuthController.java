package re.edu.hw.ss14.ex02.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import re.edu.hw.ss14.ex02.dto.request.FormLogin;
import re.edu.hw.ss14.ex02.dto.request.FormRegister;
import re.edu.hw.ss14.ex02.dto.response.JwtResponse;
import re.edu.hw.ss14.ex02.service.IAuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody FormLogin request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody FormRegister request) {
        return authService.register(request);
    }
}
