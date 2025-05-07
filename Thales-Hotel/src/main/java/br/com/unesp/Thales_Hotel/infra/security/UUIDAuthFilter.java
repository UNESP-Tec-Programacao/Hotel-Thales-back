package br.com.unesp.Thales_Hotel.infra.security;

import br.com.unesp.Thales_Hotel.services.LoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static java.lang.StringTemplate.STR;

@Component
public class UUIDAuthFilter extends OncePerRequestFilter {


    private final LoginService loginService;

    public UUIDAuthFilter(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Autenticando: " + request.getRequestURI());

        String path = request.getRequestURI();
        if (path.startsWith("/public")) {
            filterChain.doFilter(request, response);
            return;
        }

        String uuid = request.getHeader("Authorization");
        System.out.println("UUID recebido: " + uuid);

        if (uuid == null || !loginService.verifyIfUserIsLoggedByUuid(uuid)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado");
            return;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(uuid, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/public");
    }
}