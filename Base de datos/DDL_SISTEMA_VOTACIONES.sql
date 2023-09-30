
CREATE SCHEMA VOTACIONES AUTHORIZATION master;
COMMENT ON SCHEMA  VOTACIONES IS ' SISTEMA DE VOTACIONES DE GUATEMALA';

CREATE USER  AP_MS_VOTACIONES PASSWORD '123$';



--********************** TABLA 1 ***********************--
CREATE TABLE votaciones.TipoCatalogo (
    idTipoCatalogo SERIAL PRIMARY KEY,
    descripcion VARCHAR(255),
    fechaAdicion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	fechaModificacion TIMESTAMP
);

--********************** TABLA 2 ***********************--
CREATE TABLE votaciones.Catalogo (
    idCatalogo SERIAL PRIMARY KEY,
    idTipoCatalogo BIGINT,
    codigo VARCHAR(255),
    descripcion VARCHAR(255),
    fechaAdicion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	fechaModificacion TIMESTAMP,
    departamento BIGINT,
	municipio BIGINT,
    tipoCandidato BIGINT,
    partidoPolítico BIGINT,
    nombre VARCHAR(255),
    edad VARCHAR(255),
    Foto VARCHAR(255),
    ideologia VARCHAR(255),
    FOREIGN KEY (idTipoCatalogo) REFERENCES votaciones.TipoCatalogo (idTipoCatalogo)
);


--********************** TABLA 3 ***********************--
CREATE TABLE votaciones.Usuario (
    dpi VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    departamento VARCHAR(255),
    municipio VARCHAR(255),
    usuario VARCHAR(255),
    password VARCHAR(255),	
    rol VARCHAR(255),
    fechaAdicion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


--********************** TABLA 4 ***********************--
CREATE TABLE votaciones.Voto (
    idVoto bigserial PRIMARY KEY,
    dpi VARCHAR(255),
    fechaVotacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (dpi) REFERENCES votaciones.Usuario (dpi)
);



--********************** TABLA 5 ***********************--
CREATE TABLE votaciones.DetalleVoto (
    idDetalle bigserial PRIMARY KEY,
    idVoto BIGINT,
    tipoVoto VARCHAR(255),
    idElecto VARCHAR(255),
    FOREIGN KEY (idVoto) REFERENCES votaciones.Voto (idVoto)
);


GRANT  USAGE, SELECT, UPDATE ON all sequences IN schema VOTACIONES to AP_MS_VOTACIONES;
GRANT SELECT, INSERT, UPDATE ON all tables IN schema VOTACIONES to  AP_MS_VOTACIONES;
GRANT USAGE ON SCHEMA VOTACIONES TO AP_MS_VOTACIONES;


-- Altera la secuencia para que comience en 362 por todos los inserts 
ALTER SEQUENCE votaciones.catalogo_idcatalogo_seq RESTART WITH 362;