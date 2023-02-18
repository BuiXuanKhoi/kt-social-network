package com.example.socialnetworkproject.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialnetworkproject.constants.JwtDocument;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.security.services.UserDetail;
import com.example.socialnetworkproject.security.services.UserDetailServiceImpl;
import com.example.socialnetworkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public JwtAuthenticationFilter(UserService userService, UserDetailServiceImpl userDetailService) {
        this.userService = userService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader(JwtDocument.JWT_HEADER);
        String token = JwtUtils.parse(headerAuth);

        DecodedJWT decodedJWT = JwtUtils.verify(token, request);
        String userNameFromToken = decodedJWT.getClaim(JwtDocument.JWT_CLAIMS_KEY).toString();
        UserDetails userDetail = userDetailService.loadUserByUsername(userNameFromToken);
        String userName = userDetail.getUsername();
        String password = userDetail.getPassword();
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName,
                password,
                authorities
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
