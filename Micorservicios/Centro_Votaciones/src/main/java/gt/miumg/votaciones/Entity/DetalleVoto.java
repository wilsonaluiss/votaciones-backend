/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "detallevoto", schema = "votaciones")
public class DetalleVoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private Long idDetalle;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idvoto")
    private Voto voto;

    @Column(name = "tipovoto")
    private String tipoVoto;

    @Column(name = "idelecto")
    private String idElecto;

    public DetalleVoto(Voto voto, String tipoVoto, String idElecto) {
        this.voto = voto;
        this.tipoVoto = tipoVoto;
        this.idElecto = idElecto;
    }


}
