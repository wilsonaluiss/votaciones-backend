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
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "voto", schema = "votaciones")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvoto")
    private Long idVoto;

    /*@ManyToOne
    @JoinColumn(name = "dpi")*/
    @Column(name = "dpi")
    private String usuario;

    @Column(name = "fechavotacion", nullable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date fechaVotacion;

    public Voto(String usuario, Date fechaVotacion) {
        this.usuario = usuario;
        this.fechaVotacion = fechaVotacion;
    }

}
