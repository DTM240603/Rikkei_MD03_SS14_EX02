package re.edu.hw.ss14.ex02.service;

import re.edu.hw.ss14.ex02.dto.request.FormLogin;
import re.edu.hw.ss14.ex02.dto.request.FormRegister;
import re.edu.hw.ss14.ex02.dto.response.JwtResponse;

public interface IAuthService {

    JwtResponse login(FormLogin request);

    String register(FormRegister request);
}
