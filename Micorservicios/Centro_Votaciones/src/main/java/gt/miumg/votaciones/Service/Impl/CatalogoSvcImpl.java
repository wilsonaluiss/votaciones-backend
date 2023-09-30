/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Service.Impl;

import gt.miumg.votaciones.Entity.Catalogo;
import gt.miumg.votaciones.Repository.CatalogosRepository;
import gt.miumg.votaciones.dto.CandidatoDto;
import gt.miumg.votaciones.dto.PartidoDto;
import gt.miumg.votaciones.projection.CandidatosProjection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public class CatalogoSvcImpl {

    @Autowired
    CatalogosRepository catalogoRepository;

    public ResponseEntity<?> findDepartamentos() {
        return ResponseEntity.ok(catalogoRepository.findDepartamentos());
    }

    public ResponseEntity<?> findMunicipiosByDepartemento(String departamento) {
        return ResponseEntity.ok(catalogoRepository.findMunicipiosByDepartemento(departamento));
    }

    public ResponseEntity<?> findTiposCandidatos() {
        return ResponseEntity.ok(catalogoRepository.findTiposCandidatos());
    }

    public ResponseEntity<?> findPartidos() {
        return ResponseEntity.ok(catalogoRepository.findPartidos());
    }

    public ResponseEntity<?> findPresidentes() {
        return ResponseEntity.ok(catalogoRepository.findPresidentes());
    }

    public List<CandidatosProjection> findAlcaldesByUser(String municipio) {
        return (catalogoRepository.findAlcaldesByUser(municipio));
    }

    public ResponseEntity<?> findDiputadosByUser(String idDepartamento) {
        return ResponseEntity.ok(catalogoRepository.findDiputadosByUser(idDepartamento));
    }

    public ResponseEntity<?> savePartido(PartidoDto datos) {

        Catalogo newCatalogo = new Catalogo(new Long(4),
                datos.getCodigo(),
                datos.getNombrePartido(),
                datos.getFoto(),
                datos.getIdeologia(),
                new Date());
        if (datos.getIdCatalogo() != 0) {
            newCatalogo.setIdCatalogo(new Long(datos.getIdCatalogo()));
            newCatalogo.setFechaModificacion(new Date());
        }
        return ResponseEntity.ok(catalogoRepository.save(newCatalogo));
    }

    public ResponseEntity<?> saveCandidato(CandidatoDto datos) {
        Catalogo newCatalogo = new Catalogo(new Long(5),
                new Date(),
                datos.getIdDepartamento(),
                datos.getIdMunicipio(),
                datos.getIdTipoCandidato(),
                datos.getIdPartido(),
                datos.getNombre(),
                datos.getEdad(),
                datos.getFoto(),
                datos.getIdeologia());
        if (datos.getIdCatalogo() != 0) {
            newCatalogo.setIdCatalogo(new Long(datos.getIdCatalogo()));
            newCatalogo.setFechaModificacion(new Date());
        }
        return ResponseEntity.ok(catalogoRepository.save(newCatalogo));
    }
}
