package com.example.socialnetworkproject.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialnetworkproject.constants.JwtDocument;
import com.example.socialnetworkproject.constants.Role;
import com.example.socialnetworkproject.security.services.UserDetailServiceImpl;
import com.example.socialnetworkproject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);


    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public JwtAuthTokenFilter( UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader(JwtDocument.JWT_HEADER);
        String token = JwtUtils.parse(headerAuth);
        logger.info(request.toString());
        logger.info(request.getRequestURI());
        logger.info(request.getContentType() + " content");

        String[] contents = request.getParameterValues("images");
        System.out.println(Arrays.toString(contents));

        if (Boolean.TRUE.equals(JwtUtils.isTokenExist(headerAuth))){

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

        }
        filterChain.doFilter(request, response);
    }
}
