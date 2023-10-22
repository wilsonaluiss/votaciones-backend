package gt.miumg.votaciones.Repository;

import gt.miumg.votaciones.Entity.DetalleVoto;
import gt.miumg.votaciones.projection.CatalogosProjection;
import gt.miumg.votaciones.projection.ResultadoVotacionesProjection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Oscar
 */
public interface DetalleVotoRepository extends CrudRepository<DetalleVoto, Object> {

//consulta para saber si el ciudadano ya votó
    @Query(value = " SELECT COUNT(*) > 0 AS ha_votado\n"
            + "FROM votaciones.voto v\n"
            + "WHERE v.dpi = ?\n"
            + "  AND v.fechavotacion >= CURRENT_DATE - INTERVAL '2 days'\n"
            + "  AND v.fechavotacion <= CURRENT_DATE + INTERVAL '2 days'", nativeQuery = true)
    public boolean yaVotoCiudadano(String dpi);

//consulta para traer los votos para alguna grafica
    @Query(value = "SELECT\n"
            + "    ca.descripcion AS PartidoPolitico, -- Nombre del partido político\n"
            + "    c.nombre AS NombreCandidato, -- Nombre del candidato\n"
            + "    cat.descripcion AS TipoCandidato, -- Tipo de candidato\n"
            + "    mun.descripcion AS Municipio, -- Nombre del municipio\n"
            + "    dep.descripcion AS Departamento, -- Nombre del departamento\n"
            + "    COUNT(dv.idvoto) AS CantidadVotos -- Cantidad de votos\n"
            + "FROM\n"
            + "    votaciones.voto v\n"
            + "INNER JOIN\n"
            + "    votaciones.detallevoto dv ON v.idvoto = dv.idvoto -- Unimos con la tabla de detalles de votos\n"
            + "INNER JOIN\n"
            + "    votaciones.catalogo c ON CAST(dv.idelecto AS integer) = c.idcatalogo  -- Unimos con la tabla de candidatos\n"
            + "LEFT JOIN\n"
            + "    votaciones.catalogo ca ON c.partidopolítico = ca.idcatalogo -- Unimos con la tabla de partidos políticos\n"
            + "LEFT JOIN\n"
            + "    votaciones.catalogo mun ON c.municipio = mun.idcatalogo -- Unimos con la tabla de municipios a través del candidato\n"
            + "LEFT JOIN\n"
            + "    votaciones.catalogo dep ON c.departamento = dep.idcatalogo -- Unimos con la tabla de departamentos a través del candidato\n"
            + "INNER JOIN\n"
            + "    votaciones.catalogo cat ON c.tipocandidato  = cat.idcatalogo -- Unimos con la tabla de tipos de candidatos\n"
            + "WHERE\n"
            + "    v.fechavotacion >= CURRENT_DATE - INTERVAL '2 days' -- Filtramos votos de los últimos dos días antes\n"
            + "    AND v.fechavotacion <= CURRENT_DATE + INTERVAL '2 days' -- y después de la fecha actual\n"
            + "    AND ( :municipio =  0 OR c.municipio = :municipio)\n"
            + "    AND ( :partido = 0 OR c.partidopolítico = :partido)\n"
            + "    AND ( :departamento = 0 OR c.departamento = :departamento)\n"
            + "GROUP BY\n"
            + "    ca.descripcion, c.nombre, cat.descripcion, mun.descripcion, dep.descripcion",
            nativeQuery = true)
    public List<ResultadoVotacionesProjection> findVotos(@Param("municipio") Integer municipio,
            @Param("partido") Integer partido,
            @Param("departamento") Integer departamento
    );

}
