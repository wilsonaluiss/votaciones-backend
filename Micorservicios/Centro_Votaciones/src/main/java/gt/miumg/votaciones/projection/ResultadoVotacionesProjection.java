/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.miumg.votaciones.projection;

/**
 *
 * @author Oscar
 */
public interface ResultadoVotacionesProjection {

    String getPartidoPolitico();

    String getNombreCandidato();

    String getTipoCandidato();

    String getMunicipio();

    String getDepartamento();

    Long getCantidadVotos();
}
