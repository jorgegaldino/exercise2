CREATE TABLE ow_microservice.marca
(
    id numeric(10,0) NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT marca_pkey PRIMARY KEY (id),
    CONSTRAINT nome_unique UNIQUE (nome)
)

TABLESPACE pg_default;

ALTER TABLE ow_microservice.marca
    OWNER to postgres;
COMMENT ON TABLE ow_microservice.marca
    IS 'Tabela de Marcas';
    
CREATE TABLE ow_microservice.usuario
(
    nome character varying(150) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (email)
)

TABLESPACE pg_default;

ALTER TABLE ow_microservice.usuario
    OWNER to postgres;
COMMENT ON TABLE ow_microservice.usuario
    IS 'Tabela de Usuários';
    
CREATE TABLE ow_microservice.patrimonio
(
    num_tombo integer NOT NULL DEFAULT nextval('ow_microservice.patrimonio_num_tombo_seq'::regclass),
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    marca_id integer NOT NULL,
    CONSTRAINT patrimonio_pkey PRIMARY KEY (num_tombo),
    CONSTRAINT marca_fk FOREIGN KEY (marca_id)
        REFERENCES ow_microservice.marca (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE ow_microservice.patrimonio
    OWNER to postgres;
COMMENT ON TABLE ow_microservice.patrimonio
    IS 'Tabela de Patrimonios';

COMMENT ON CONSTRAINT marca_fk ON ow_microservice.patrimonio
    IS 'Foreign Key para Marca';        