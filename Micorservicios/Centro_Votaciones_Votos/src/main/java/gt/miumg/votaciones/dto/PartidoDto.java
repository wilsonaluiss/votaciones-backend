/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.votaciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Oscar
 */
@Data
@NoArgsConstructor
public class PartidoDto {

    int idCatalogo;
    String codigo;
    String nombrePartido;
    String ideologia;
    String foto;
}
