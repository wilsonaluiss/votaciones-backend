/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Entity;


import java.security.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
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
@Table(name = "TipoCatalogo", schema = "votaciones")
public class TipoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCatalogo;

    private String descripcion;

    @Column(name = "fechaAdicion", nullable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
}
