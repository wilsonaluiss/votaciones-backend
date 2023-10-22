package gt.miumg.usuarios;

import gt.miumg.usuarios.Entity.Usuario;
import gt.miumg.usuarios.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CentroVotacioesUsuariosApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CentroVotacioesUsuariosApplication.class, args);
    }

   
}
