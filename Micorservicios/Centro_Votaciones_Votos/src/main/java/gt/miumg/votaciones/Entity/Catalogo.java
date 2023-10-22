/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Temporal;

/**
 *
 * @author Oscar
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "catalogo", schema = "votaciones")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcatalogo")
    Long idCatalogo;

    @Column(name = "idtipocatalogo")
    Long idTipoCatalogo;

    @Column(name = "codigo")
    String codigo;

    @Column(name = "descripcion")
    String descripcion;

    @Column(name = "fechaadicion", nullable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //@Temporal(TemporalType.TIMESTAMP)
    Date fechaAdicion; 

 @Column(name = "fechamodificacion", nullable = true)
    //@Temporal(TemporalType.TIMESTAMP)
    Date fechaModificacion;


    @Column(name = "departamento")
    Long departamento;

    @Column(name = "municipio")
    Long municipio;

    @Column(name = "tipocandidato")
    Long tipoCandidato;

    @Column(name = "partidopolítico")
    Long partidoPolítico;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "edad")
    String edad;

    @Column(name = "foto")
    String Foto;

    @Column(name = "ideologia")
    String ideologia;

    public Catalogo(Long idTipoCatalogo,  String codigo, String descripcion, String Foto, String ideologia, Date fechaAdicion) {
        this.idTipoCatalogo = idTipoCatalogo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.Foto = Foto;
        this.ideologia = ideologia;
        this.fechaAdicion = fechaAdicion;
    }

    public Catalogo(Long idTipoCatalogo,  Date fechaAdicion, Long departamento, Long municipio, Long tipoCandidato, Long partidoPolítico, String nombre, String edad, String Foto, String ideologia) {
        this.idTipoCatalogo = idTipoCatalogo;
        this.fechaAdicion = fechaAdicion;
        this.departamento = departamento;
        this.municipio = municipio;
        this.tipoCandidato = tipoCandidato;
        this.partidoPolítico = partidoPolítico;
        this.nombre = nombre;
        this.edad = edad;
        this.Foto = Foto;
        this.ideologia = ideologia;
    }

}
