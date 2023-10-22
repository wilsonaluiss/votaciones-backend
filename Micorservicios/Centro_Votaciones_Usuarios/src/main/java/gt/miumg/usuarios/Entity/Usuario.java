/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.usuarios.Entity;

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

/**
 *
 * @author Oscar
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Usuario", schema = "votaciones")
public class Usuario {

    @Id
    private String dpi;
    private String nombre;
    private String apellido;
    private String departamento;
    private String municipio;
    private String usuario;
    private String password;

    private String rol;

    @Column(name = "fechaadicion", nullable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;

}
