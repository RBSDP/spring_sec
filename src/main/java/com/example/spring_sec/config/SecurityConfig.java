//


package com.example.spring_sec.config;

import com.example.spring_sec.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    // AuthenticationProvider (Spring Security 6 style)
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService myUserDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(myUserDetailsService); // 👈 REQUIRED constructor

        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    //  DEV ONLY (use BCrypt in real apps)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    //  Security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register","/login").permitAll().anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }
}





//package com.example.spring_sec.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    package com.example.spring_sec.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfig {
//
//        // ✅ AuthenticationProvider (Spring Security 6 style)
//        @Bean
//        public AuthenticationProvider authenticationProvider(
//                UserDetailsService userDetailsService,
//                PasswordEncoder passwordEncoder) {
//
//            DaoAuthenticationProvider provider =
//                    new DaoAuthenticationProvider(userDetailsService); // 👈 REQUIRED constructor
//
//            provider.setPasswordEncoder(passwordEncoder);
//            return provider;
//        }
//
//        // ⚠️ DEV ONLY (use BCrypt in real apps)
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return NoOpPasswordEncoder.getInstance();
//        }
//
//        // ✅ Security rules
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//            http
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(auth -> auth
//                            .anyRequest().authenticated()
//                    )
//                    .httpBasic(Customizer.withDefaults())
//                    .sessionManagement(session ->
//                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    );
//
//            return http.build();
//        }
//    }
//
//
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Bean
////    public AuthenticationProvider authProvider(
////            UserDetailsService userDetailsService,
////            PasswordEncoder passwordEncoder) {
////
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setUserDetailsService(userDetailsService);
////        provider.setPasswordEncoder(passwordEncoder);
////        return provider;
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return NoOpPasswordEncoder.getInstance(); // dev only
////    }
//////    public AuthenticationProvider authProvider(){
//////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//////        provider.setUserDetailsService(userDetailsService);
//////        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//////        return provider;
//////    }
////
////    @Bean
////    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
////
////        http.csrf(customizer -> customizer.disable())
////            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
////                    .httpBasic(Customizer.withDefaults())
////            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////
////        return http.build();
////    }
////
//////    @Bean
//////    public UserDetailsService userDetailsService(){
//////        UserDetails user=User
//////                .withDefaultPasswordEncoder()
//////                .username("navin")
//////                .password("n@123")
//////                .roles("USER")
//////                .build();
//////
//////        UserDetails  admin=User
//////                .withDefaultPasswordEncoder()
//////                .username("admin")
//////                .password("admin@789")
//////                .roles("ADMIN")
//////                .build();
//////
//////        return new InMemoryUserDetailsManager(user);
//////    }
//
//
//
//
//
//}