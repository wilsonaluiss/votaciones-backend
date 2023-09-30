/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Service.Impl;

import com.nimbusds.jose.JOSEException;
import gt.miumg.votaciones.Entity.Usuario;
import gt.miumg.votaciones.Repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gt.miumg.votaciones.components.Exceptiones;
import gt.miumg.votaciones.dto.LoginRequest;
import gt.miumg.votaciones.security.JwtTokenProvider;
import gt.miumg.votaciones.security.LoginResponse;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Servicio para operaciones relacionadas con usuarios.
 *
 * @author Oscar
 */
@Service
public class UsuarioSvcImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param dato El usuario a registrar.
     */
    public void saveUsuario(Usuario dato) {
        Usuario usuario = this.repository.findUsuarioByusername(dato.getUsuario());
        /*if (!usuario.getDpi().equals(null)) {
            throw new Exceptiones();
        }*/
        // Hashea la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(dato.getPassword());
        dato.setPassword(hashedPassword);

        dato.setRol("CIUDADANO");
        this.repository.save(dato);
    }

    /**
     * Autentica a un usuario y genera un token JWT para él.
     *
     * @param loginRequest Los datos de inicio de sesión del usuario.
     * @return Un ResponseEntity con el token JWT y otros datos si la
     * autenticación es exitosa.
     */
    public ResponseEntity<?> logueo(LoginRequest loginRequest) throws JOSEException {
        System.out.println("Usuario:::" + loginRequest.getUsername());
        System.out.println("pass:::" + loginRequest.getPassword());
        Usuario user = repository.findUsuarioByusername(loginRequest.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Verificar si la contraseña proporcionada coincide con la almacenada en la base de datos
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        // Autenticación exitosa, generar el token JWT y devolver los datos adicionales
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = generateJwtToken(loginRequest.getUsername());

        LoginResponse loginResponse = new LoginResponse(jwt, user.getDepartamento(), user.getMunicipio(), user.getRol());
        return ResponseEntity.ok(loginResponse);
    }

    public String generateJwtToken(String authentication) throws JOSEException {
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = this.repository.findUsuarioByusername(username);
        if (user == null) {
            throw new Exceptiones();
        }
        // Devuelve un UserDetails personalizado que representa al usuario cargado desde la base de datos
        return new User(
                user.getUsuario(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
