package app.configs;

import app.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.UserDetailsServiceImpl;
import security.TokenAuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new AuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/*");
        web.ignoring().antMatchers("/images/animal/*");
        web.ignoring().antMatchers("/mongo/*");
        web.ignoring().antMatchers("/es/*");
        web.ignoring().antMatchers("/es");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}

//.antMatchers("/api/auth/*").permitAll().anyRequest().permitAll()