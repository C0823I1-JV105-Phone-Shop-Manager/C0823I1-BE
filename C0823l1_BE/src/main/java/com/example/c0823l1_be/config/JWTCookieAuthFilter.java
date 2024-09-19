//package com.example.c0823l1_be.config;
//
//
//import com.example.c0823l1_be.security.JWTUtils;
//import com.example.c0823l1_be.service.StaffUserDetailsService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTCookieAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    @Autowired
//    private StaffUserDetailsService detailsService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
////        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String userEmail;
//        String jwtToken = null;
//
//
////        if (authHeader == null || !authHeader.startsWith("Bearer")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
//
//        for (Cookie cookie : request.getCookies()) {
//            if (cookie.getName().equals("jwt")) {
//                jwtToken = cookie.getValue();
////                System.out.println(cookie.getValue());
//            }
//        }
//        if (jwtToken == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
////        jwtToken = authHeader.substring(7);
//        userEmail = jwtUtils.extractUsername(jwtToken);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = detailsService.loadUserByUsername(userEmail);
//            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
//    }
//}
