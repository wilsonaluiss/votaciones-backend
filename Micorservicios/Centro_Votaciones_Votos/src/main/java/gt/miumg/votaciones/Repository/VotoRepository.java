/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.miumg.votaciones.Repository;

import org.springframework.data.repository.CrudRepository;
import gt.miumg.votaciones.Entity.Voto;

/**
 *
 * @author Oscar
 */
public interface VotoRepository extends CrudRepository<Voto, Object>{
    
}
