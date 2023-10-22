package gt.miumg.votaciones;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

@SpringBootApplication
public class CentroVotacionesApplication {

    public static void main(String[] args) {
        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
        System.out.println("Secret key: " + secretKey);
        SpringApplication.run(CentroVotacionesApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200/").allowedMethods("*").allowedHeaders("*");

            }
        };
    }
}
