/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.miumg.usuarios.components;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Oscar
 */
@Service
@Getter
@Setter
public class MsConsummer {

    @Value("${consumo-ms-renap.pingUrlInterna}")
    private String urlRenap;

    /*
    @Value("${consumo-ms-otro.pingUrlInterna}")
    private String urlOtro;
     */
    public boolean consumirServicio(String tipo, String dpi) {//renap u otro
        String url = "";
        if (tipo.equals("renap")) {
            url = "/renap/consultarDPI/" + dpi;
        } else {
            url = "/otro/consultarDPI/" + dpi;
        }
        ResponseEntity<?> response = response(null, url, Object.class, HttpMethod.GET);
        System.out.println("Response::" + response.getBody());
        Boolean responseBody = (Boolean) response.getBody();
        return responseBody;
    }

    public <T> ResponseEntity<?> response(@Nullable Object body, @NotNull @NotBlank String url, @NotNull Class<T> klass, @NotNull HttpMethod type) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String ruta = this.urlRenap + "WebService" + url;
            System.out.println(ruta);

            HttpEntity<?> requestBody = new HttpEntity<>(body, headers);
            return restTemplate.exchange(ruta, type, requestBody, klass);
        } catch (HttpClientErrorException e) {
            ResponseEntity<?> response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
            return response;
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
