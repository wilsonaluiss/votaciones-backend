/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.miumg.votaciones.Repository;

import gt.miumg.votaciones.Entity.Catalogo;
import gt.miumg.votaciones.projection.CandidatosProjection;
import gt.miumg.votaciones.projection.CatalogosProjection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Oscar
 */
public interface CatalogosRepository extends CrudRepository<Catalogo, Object> {

    //obtiene los departamentos
    @Query(value = "select ca.idcatalogo Id, ca.codigo, Descripcion from votaciones.catalogo ca\n"
            + "where ca.idtipocatalogo = '1'",
            nativeQuery = true)
    public List<CatalogosProjection> findDepartamentos();


//obtiene los municipios de un departmanto
    @Query(value = "select ca.idcatalogo Id, ca.codigo, Descripcion from votaciones.catalogo ca\n"
            + "where ca.departamento = CAST(? AS integer)",
            nativeQuery = true)
    public List<CatalogosProjection> findMunicipiosByDepartemento(String departamento);

//obtiene los tipos de candidatos
    @Query(value = "select ca.idcatalogo Id, ca.codigo, Descripcion from votaciones.catalogo ca\n"
            + "where ca.idtipocatalogo = '3'",
            nativeQuery = true)
    public List<CatalogosProjection> findTiposCandidatos();

//obtiene los partidos
    @Query(value = "select ca.idcatalogo Id, ca.codigo, Descripcion from votaciones.catalogo ca\n"
            + "where ca.idTipoCatalogo = '4'",
            nativeQuery = true)
    public List<CatalogosProjection> findPartidos();

//obtiene los presidentes
    @Query(value = "SELECT\n"
            + "        ca.idcatalogo AS IdCatalogo,\n"
            + "        tipo.idcatalogo  AS  idTipoCandidato,\n"
            + "        tipo.descripcion  as tipoCandidato,\n"
            + "        partido.idcatalogo  AS  idPartido,\n"
            + "        partido.descripcion  as partido,\n"
            + "        ca.nombre ,\n"
            + "        ca.edad ,\n"
            + "        ca.ideologia  \n"
            + "FROM\n"
            + "        votaciones.catalogo AS ca \n"
            + "INNER JOIN\n"
            + "        votaciones.catalogo tipo \n"
            + "ON tipo.idcatalogo = ca.tipocandidato --para el tipo candidato \n"
            + "INNER JOIN\n"
            + "        votaciones.catalogo partido \n"
            + "ON partido.idcatalogo = ca.partidopolítico  --para el partido  \n"
            + "WHERE\n"
            + "        ca.tipocandidato IN (357, 358) ;", nativeQuery = true)
    public List<CandidatosProjection> findPresidentes();

//obtiene los alcaldes
    @Query(value = " SELECT\n"
            + "        ca.idcatalogo AS IdCatalogo,\n"
            + "        tipo.idcatalogo AS idTipoCandidato,\n"
            + "        tipo.descripcion AS tipoCandidato,\n"
            + "        dep.idcatalogo AS idDepartamento,\n"
            + "        dep.descripcion AS Departamento,\n"
            + "        mun.idcatalogo AS idMunicipio,\n"
            + "        mun.descripcion AS municipio,\n"
            + "        ca.partidopolítico AS idPartido,\n"
            + "        partido.descripcion AS partido,\n"
            + "        ca.nombre,\n"
            + "        ca.edad,\n"
            + "        ca.ideologia \n"
            + "    FROM\n"
            + "        votaciones.catalogo AS ca \n"
            + "    INNER JOIN\n"
            + "        votaciones.catalogo tipo \n"
            + "            ON tipo.idcatalogo = ca.tipocandidato \n"
            + "    left JOIN\n"
            + "        votaciones.catalogo partido \n"
            + "            ON partido.idcatalogo = ca.partidopolítico \n"
            + "    LEFT JOIN\n"
            + "        votaciones.catalogo mun \n"
            + "            ON mun.idcatalogo = ca.municipio \n"
            + "    LEFT JOIN\n"
            + "        votaciones.catalogo dep \n"
            + "            ON dep.idcatalogo = ca.municipio \n"
            + "    WHERE\n"
            + "        ca.idcatalogo IN (\n"
            + "            SELECT\n"
            + "                DISTINCT ca.idcatalogo         \n"
            + "            FROM\n"
            + "                votaciones.catalogo AS ca         \n"
            + "            INNER JOIN\n"
            + "                votaciones.catalogo cata \n"
            + "                    ON cata.idcatalogo = ca.partidopolítico         \n"
            + "            WHERE\n"
            + "                tipo.idcatalogo = 359         \n"
            + "                AND ca.municipio = CAST(? AS integer)     \n"
            + "        ) --para que sea alcaldes de su municipio",
            nativeQuery = true)
    public List<CandidatosProjection> findAlcaldesByUser(String municipio);

//obtiene los Diputados nacionales y de cada departamento
    @Query(value = "SELECT\n"
            + "    ca.idcatalogo AS IdCatalogo,\n"
            + "    ca.idtipocatalogo  AS  idTipoCandidato,\n"
            + "    cat.descripcion  as tipoCandidato,\n"
            + "    dep.idcatalogo as idDepartamento,\n"
            + "    dep.descripcion as Departamento,\n"
            + "    ca.partidopolítico  AS  idPartido,\n"
            + "    cata.descripcion  as partido,\n"
            + "    ca.nombre ,\n"
            + "    ca.edad ,\n"
            + "    ca.ideologia  \n"
            + "FROM\n"
            + "    votaciones.catalogo AS ca \n"
            + "INNER JOIN\n"
            + "    votaciones.catalogo cat --para el tipo candidato \n"
            + "    ON cat.idcatalogo = ca.tipocandidato \n"
            + "INNER JOIN\n"
            + "    votaciones.catalogo cata --para el partido  \n"
            + "    ON cata.idcatalogo = ca.partidopolítico  \n"
            + "LEFT JOIN\n"
            + "    votaciones.catalogo dep --para el departamento  \n"
            + "    ON dep.idcatalogo = ca.departamento  \n"
            + "WHERE\n"
            + "    cat.idcatalogo IN (361) \n"
            + "    OR (\n"
            + "        cat.idcatalogo IN (360)  \n"
            + "        AND ca.departamento IN (CAST(? AS integer))\n"
            + "    )--para que sea diputado nacional o de su dep",
            nativeQuery = true)
    public List<CandidatosProjection> findDiputadosByUser(String idDepartamento);

}
