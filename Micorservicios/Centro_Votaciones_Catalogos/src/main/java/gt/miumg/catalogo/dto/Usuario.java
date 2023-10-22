/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.catalogo.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Oscar
 */
@Data
public class Usuario {

    private String dpi;
    private String nombre;
    private String apellido;
    private String departamento;
    private String municipio;
    private String usuario;
    private String password;
    private String rol;
    private Date fechaAdicion;
}
