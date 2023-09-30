/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Controller;

import gt.miumg.votaciones.Service.Impl.VotoSvcImpl;
import gt.miumg.votaciones.dto.ResponseDto;
import gt.miumg.votaciones.dto.VotacionDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gt.miumg.votaciones.components.Exceptiones;
import gt.miumg.votaciones.dto.ParametrosBusquedaVotacionDto;

/**
 *
 * @author Oscar
 */
@RestController
@Slf4j
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    VotoSvcImpl servicio;

    @PostMapping(value = "/save/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> consultar(@RequestBody List<VotacionDto> dato, @PathVariable(required = true) String usuario) {
        try {
            servicio.saveVotacion(dato, usuario);
            ResponseDto response = new ResponseDto("Su voto fue ejecutado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exceptiones ex) {
            ResponseDto response = new ResponseDto("Ocurrio un error ingresando el voto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping(value = "/consultarVotos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> consultar(@RequestBody ParametrosBusquedaVotacionDto datos) {
        return ResponseEntity.ok(servicio.getVotos(datos)).getBody();
    }

}
