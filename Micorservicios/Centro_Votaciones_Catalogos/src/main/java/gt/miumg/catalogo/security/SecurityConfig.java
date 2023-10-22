package gt.miumg.catalogo.security;

import gt.miumg.catalogo.components.MsConsummer;
import gt.miumg.catalogo.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MsConsummer client;

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // Configura tus proveedores de autenticación aquí, por ejemplo, en memoria o con una base de datos.
        // Aquí, configuramos un proveedor de autenticación personalizado que utiliza el repositorio de usuarios.
        try {
            auth.userDetailsService(username -> {
                Usuario user = client.consumirServicio(username);
                if (user == null) {
                    throw new UsernameNotFoundException("Usuario no encontrado");
                }
                System.out.println("CONTRASEÑA ENCRIPTADA::::" + user.getPassword());
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsuario())
                        .password(user.getPassword()) // Debes asegurarte de que la contraseña esté encriptada
                        .roles("USER") // Asigna los roles/granted authorities del usuario
                        .build();
            }).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            System.out.println("");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configura las reglas de seguridad aquí.
        http.csrf().disable()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()  // Permite acceso a /usuario/login sin autenticación
                .anyRequest().authenticated()
                .and()
                .formLogin().disable() // Deshabilita el formulario de inicio de sesión predeterminado
                .httpBasic().disable(); // Deshabilita la autenticación HTTP básica
    }
}
