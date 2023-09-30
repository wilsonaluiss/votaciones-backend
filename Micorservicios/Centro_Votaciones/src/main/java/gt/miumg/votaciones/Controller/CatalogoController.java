/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Controller;

import gt.miumg.votaciones.Entity.Catalogo;
import gt.miumg.votaciones.Service.Impl.CatalogoSvcImpl;
import gt.miumg.votaciones.dto.CandidatoDto;
import gt.miumg.votaciones.dto.PartidoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Oscar
 */
//@Api
@RestController
@Slf4j
@RequestMapping("/catalogos")
public class CatalogoController {

    @Autowired
    CatalogoSvcImpl catalogoImpl;

    @GetMapping(value = "/findDepartamentos")
//    @ApiOperation(value = "obtiene el catalogo de los departamento")
    public ResponseEntity<?> findDepartamentos() {
        return ResponseEntity.ok(catalogoImpl.findDepartamentos()).getBody();
    }

    @GetMapping(value = "/findMunicipiosByDepartemento/{departamento}")
//    @ApiOperation(value = "obtiene el catalogo de los municipios a partir del departamento")
    public ResponseEntity<?> findMunicipiosByDepartemento(@PathVariable(required = true) String departamento) {
        return ResponseEntity.ok(catalogoImpl.findMunicipiosByDepartemento(departamento)).getBody();
    }

    @GetMapping(value = "/findTiposCandidatos")
//    @ApiOperation(value = "obtiene el catalogo de los departamento")
    public ResponseEntity<?> findTiposCandidatos() {
        return ResponseEntity.ok(catalogoImpl.findTiposCandidatos()).getBody();
    }

    @GetMapping(value = "/findPartidos")
//    @ApiOperation(value = "obtiene el catalogo de los partidos politicos ingresados")
    public ResponseEntity<?> findPartidos() {
        return ResponseEntity.ok(catalogoImpl.findPartidos()).getBody();
    }

    @GetMapping(value = "/findPresidentes")
//    @ApiOperation(value = "obtiene el catalogo de los presidentes")
    public ResponseEntity<?> findPresidentes() {
        return ResponseEntity.ok(catalogoImpl.findPresidentes()).getBody();
    }

    @GetMapping(value = "/findAlcaldesByUser/{municipio}")
//    @ApiOperation(value = "obtiene el catalogo de los alcaldes a partir del municipio del usuario registrado")
    public ResponseEntity<?> findAlcaldesByUser(@PathVariable(required = true) /*@ApiParam(value = "noManifiesto")*/ String municipio) {
        return ResponseEntity.ok(catalogoImpl.findAlcaldesByUser(municipio));
    }

    @GetMapping(value = "/findDiputadosByUser/{departamento}")
//    @ApiOperation(value = "obtiene el catalogo de los diputados a partir del departamento del usuario registrado")
    public ResponseEntity<?> findDiputadosByUser(@PathVariable(required = true) /*@ApiParam(value = "noManifiesto")*/ String departamento) {
        return ResponseEntity.ok(catalogoImpl.findDiputadosByUser(departamento)).getBody();
    }

    @PostMapping(value = "/savePartido")
//    @ApiOperation(value = "guardara el objeto del nuevo catalogo")
    //@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public ResponseEntity<?> savePartido(@RequestBody PartidoDto dato) {
        return ResponseEntity.ok(catalogoImpl.savePartido(dato)).getBody();
    }

    @PostMapping(value = "/saveCandidato")
//    @ApiOperation(value = "guardara el objeto del nuevo catalogo")
    //@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public ResponseEntity<?> saveCandidato(@RequestBody CandidatoDto dato) {
        return ResponseEntity.ok(catalogoImpl.saveCandidato(dato)).getBody();
    }
}
