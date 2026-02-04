
package com.example.spring_sec.config;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring_sec.model.User;
import com.example.spring_sec.service.JwtService;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {
                String authHeader = request.getHeader("Authorization");
                String token = null;
                String username = null;
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    token= authHeader.substring(7);
                    username = jwtService.extractUserName(token);
                }

                if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
                    
                    UserDetails userDetails = context.getBean(UserDetailsService.class).loadUserByUsername(username);

                    if(jwtService.validateToken(token,userDetails)){
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                }
                filterChain.doFilter(request, response);
            }
        
}