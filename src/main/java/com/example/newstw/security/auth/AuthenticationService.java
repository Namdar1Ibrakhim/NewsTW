package com.example.newstw.security.auth;


import com.example.newstw.entity.User;
import com.example.newstw.enums.Role;
import com.example.newstw.exception.UserAlreadyExistAuthenticationException;
import com.example.newstw.repository.UserRepository;
import com.example.newstw.security.config.JwtService;
import com.example.newstw.security.token.Token;
import com.example.newstw.security.token.TokenRepository;
import com.example.newstw.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    public AuthenticationResponse registerModerator(RegisterRequest request) {
        return register(request, Role.MODERATOR);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return register(request, Role.ADMIN);
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request, Role role) {
        if(repository.findByLogin(request.getLogin()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .login(request.getLogin())
                .imageUrl(request.getImageUrl())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        var savedUser = repository.save(user);
        var jwtAccessToken = jwtService.genarateAccessToken(user);


        saveUserToken(savedUser, jwtAccessToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtAccessToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getLogin(), request.getPassword())
        );
        var user = repository.findByLogin(request.getLogin()).orElseThrow();
        var jwtAccessToken = jwtService.genarateAccessToken(user);


        revokeAllUserTokens(user);
        saveUserToken(user, jwtAccessToken);
        return AuthenticationResponse.builder().accessToken(jwtAccessToken).build();
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(Math.toIntExact(user.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
