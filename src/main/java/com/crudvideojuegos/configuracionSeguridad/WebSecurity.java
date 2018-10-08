package com.crudvideojuegos.configuracionSeguridad;

import com.crudvideojuegos.model.usuario.Usuario;
import com.crudvideojuegos.model.usuario.UsuarioFacade;
import com.crudvideojuegos.model.usuario.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;

import static com.crudvideojuegos.configuracionSeguridad.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServicio userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String[] POSTEXCEPTIONS = {"/login", "/list-languages",
    "/resourceInternalization"};
    private static final String[] GETEXCEPTIONS = {"/images/**","/", "/loginHtml", "/?lang=**", "/favicon**",  "/css/**", "/js/**", "templates/**" };


    public WebSecurity(UsuarioServicio userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,POSTEXCEPTIONS).permitAll()
                .antMatchers(HttpMethod.GET, GETEXCEPTIONS).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/").usernameParameter("username").passwordParameter("password")
                .failureForwardUrl("/homeAdmin")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

   /* @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/videojuegos");
        driverManagerDataSource.setUsername("Lara");
        driverManagerDataSource.setPassword("tomb");
        return driverManagerDataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource())
                .usersByUsernameQuery("select nombre,contrasenya, enabled from usuario where nombre=?")
                .authoritiesByUsernameQuery("select nombre, role from usuario where nombre=?");
    }*/
}
