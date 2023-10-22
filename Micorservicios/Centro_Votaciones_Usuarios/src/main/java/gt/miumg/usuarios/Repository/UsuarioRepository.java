/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.miumg.usuarios.Repository;

import gt.miumg.usuarios.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Oscar
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Object>{

    @Query(value = "select * from votaciones.Usuario u where u.usuario=? limit 1",
            nativeQuery = true)
    public Usuario findUsuarioByusername(String username);
    
}
