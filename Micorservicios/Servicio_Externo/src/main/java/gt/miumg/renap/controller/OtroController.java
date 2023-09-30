/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.renap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */

@RestController
@Slf4j
@RequestMapping("WebService/otro")
public class OtroController {
    
 @GetMapping(value = "/consultarDPI/{DPI}")
//    @ApiOperation(value = "obtiene el catalogo de los municipios a partir del departamento")
    public Boolean findMunicipiosByDepartemento(@PathVariable(required = true) String DPI) {
        return true;
    }
}
