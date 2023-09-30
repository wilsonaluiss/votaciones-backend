/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Controller;

import com.nimbusds.jose.JOSEException;
import gt.miumg.votaciones.Service.Impl.UsuarioSvcImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gt.miumg.votaciones.Entity.Usuario;
import gt.miumg.votaciones.components.Exceptiones;
import gt.miumg.votaciones.components.MsConsummer;
import gt.miumg.votaciones.dto.LoginRequest;
import gt.miumg.votaciones.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Oscar
 */
@RestController
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioSvcImpl servicio;

    @Autowired
    MsConsummer client;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario dato) {
        try {
            servicio.saveUsuario(dato);
            ResponseDto response = new ResponseDto("El usuario fue ingresado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exceptiones ex) {
            ResponseDto response = new ResponseDto("El usuario ingresado ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

 @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws JOSEException {       
        return ResponseEntity.ok(servicio.logueo(loginRequest)).getBody();
    }

    /*Controladores de prueba para comprobar que el otro servicio retorne correctamente*/
    @GetMapping(value = "/findRenap/{DPI}")
    public Boolean findWSrenap(@PathVariable(required = true) String DPI) {
        return ResponseEntity.ok(client.consumirServicio("renap", DPI)).getBody();
    }

    @GetMapping(value = "/findOtro/{DPI}")
    public Boolean findWSotro(@PathVariable(required = true) String DPI) {
        return ResponseEntity.ok(client.consumirServicio("otro", DPI)).getBody();
    }
}
