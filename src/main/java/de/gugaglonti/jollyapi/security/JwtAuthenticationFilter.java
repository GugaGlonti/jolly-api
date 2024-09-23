package de.gugaglonti.jollyapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtDecoder jwtDecoder;
  private final JwtToPrincipalConverter jwtToPrincipalConverter;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    extractTokenFromHeader(request)
        .map(jwtDecoder::decode)
        .map(jwtToPrincipalConverter::convert)
        .map(UserPrincipalAuthenticationToken::new)
        .ifPresent(this::setAuthentication);
    filterChain.doFilter(request, response);
  }

  private void setAuthentication(UserPrincipalAuthenticationToken authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private Optional<String> extractTokenFromHeader(
      HttpServletRequest request
  ) {
    var header = request.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      return Optional.empty();
    }
    return Optional.of(header.substring(7));
  }
}
