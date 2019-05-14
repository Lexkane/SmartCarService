package ua.ita.smartcarservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.ita.smartcarservice.entity.Roles;
import ua.ita.smartcarservice.service.impl.UserDetailsServiceImpl;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthTokenFilter authenticationJwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("api/user/**", "/api/users/**", "/api/skills/**", "/api/workers/**", "/api/notifications/**").hasAuthority(Roles.ROLE_TECHNICAL_MANAGER.toString())
//                .antMatchers("/api/chart/**", "api/workers/**", "api/works/**", "api/booking/**", "/api/notifications/**").hasAuthority(Roles.ROLE_CAR_OWNER.toString())
//                .antMatchers("/api/techservices/**").hasAnyAuthority(Roles.ROLE_TECHNICAL_MANAGER.toString(), Roles.ROLE_CAR_OWNER.toString())
//                .antMatchers("/api/report/**").hasAnyAuthority(Roles.ROLE_WORKER.toString(), Roles.ROLE_CAR_OWNER.toString())
//
//                .antMatchers("/api/owner/**").hasAnyAuthority(Roles.ROLE_DIELER.toString(),Roles.ROLE_CAR_OWNER.toString())

//                .antMatchers("/api/dealer/getAll/**").hasAnyAuthority(Roles.ROLE_TECHNICAL_MANAGER.toString(),Roles.ROLE_DIELER.toString(),Roles.ROLE_CAR_OWNER.toString())
//                .antMatchers("/api/dealer/epplyToDealer/**").hasAnyAuthority(Roles.ROLE_TECHNICAL_MANAGER.toString(),Roles.ROLE_DIELER.toString())

//                .antMatchers("/api/dealer/getAllTradeIn/**").hasAuthority(Roles.ROLE_DIELER.toString())

//                .antMatchers("/api/dealer/allstos/**").hasAuthority(Roles.ROLE_DIELER.toString())

//                .antMatchers("/api/car/**", "/api/newcar/**").hasAnyAuthority(Roles.ROLE_DIELER.toString(),Roles.ROLE_CAR_OWNER.toString())
//                .antMatchers("/api/dealer/**").hasAnyAuthority(Roles.ROLE_DIELER.toString(), Roles.ROLE_TECHNICAL_MANAGER.toString())
                .anyRequest().permitAll();

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}