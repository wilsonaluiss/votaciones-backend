/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.usuarios.security;

import lombok.Data;

/**
 *
 * @author Oscar
 */
@Data
public class LoginResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String departamento;
    private String municipio;
    private String rol;

    public LoginResponse(String accessToken, String departamento, String municipio, String rol) {
        this.accessToken = accessToken;
        this.departamento = departamento;
        this.municipio = municipio;
        this.rol = rol;
    }
}
