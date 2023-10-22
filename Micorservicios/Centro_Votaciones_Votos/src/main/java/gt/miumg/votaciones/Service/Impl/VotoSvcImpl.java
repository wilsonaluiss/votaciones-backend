/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Service.Impl;

import gt.miumg.votaciones.Entity.DetalleVoto;
import gt.miumg.votaciones.Entity.Voto;
import gt.miumg.votaciones.Repository.DetalleVotoRepository;
import gt.miumg.votaciones.dto.ParametrosBusquedaVotacionDto;
import gt.miumg.votaciones.dto.VotacionDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import gt.miumg.votaciones.components.Exceptiones;

/**
 *
 * @author Oscar
 */
@Service
public class VotoSvcImpl {

    @Autowired
    DetalleVotoRepository repository;

    public void saveVotacion(List<VotacionDto> datos, String usuario) {
        boolean yaVoto = this.repository.yaVotoCiudadano(usuario);
        if (yaVoto) {
            throw new Exceptiones();
        }

        Voto votacion = new Voto(usuario, new Date());
        List<DetalleVoto> detallesVotaciones = new ArrayList<>();
        for (VotacionDto dato : datos) {
            DetalleVoto detalleVotacion = new DetalleVoto(votacion, dato.getTipoCandidato(), dato.getIdElecto());
            detallesVotaciones.add(detalleVotacion);
        }
        this.repository.saveAll(detallesVotaciones);

    }

    public ResponseEntity<?> getVotos(ParametrosBusquedaVotacionDto datos) {
        if (datos.getDepartamento() == null) {
            datos.setDepartamento(0);
        }
        if (datos.getPartido() == null) {
            datos.setMunicipio(0);
        }
        if (datos.getMunicipio() == null) {

            datos.setMunicipio(0);
        }
        return ResponseEntity.ok(this.repository.findVotos(datos.getMunicipio(), datos.getPartido(), datos.getDepartamento()));
    }

}
