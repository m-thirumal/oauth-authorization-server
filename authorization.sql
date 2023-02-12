-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.0
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: Thirumal

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: "authorization" | type: DATABASE --
-- DROP DATABASE IF EXISTS "authorization";
CREATE DATABASE "authorization";
-- ddl-end --
COMMENT ON DATABASE "authorization" IS E'Created Thirumal';
-- ddl-end --


-- object: public.oauth2_authorization | type: TABLE --
-- DROP TABLE IF EXISTS public.oauth2_authorization CASCADE;
CREATE TABLE public.oauth2_authorization (
	id varchar(100) NOT NULL,
	registered_client_id varchar(100) NOT NULL,
	principal_name varchar(200) NOT NULL,
	authorization_grant_type varchar(100) NOT NULL,
	authorized_scopes varchar(1000),
	attributes text,
	state varchar(500),
	authorization_code_value text,
	authorization_code_issued_at timestamptz,
	authorization_code_expires_at timestamptz,
	authorization_code_metadata text,
	access_token_value text,
	access_token_issued_at timestamptz,
	access_token_expires_at timestamptz,
	access_token_metadata text,
	access_token_type varchar(100),
	access_token_scopes varchar(1000),
	oidc_id_token_value text,
	oidc_id_token_issued_at timestamptz,
	oidc_id_token_expires_at timestamptz,
	oidc_id_token_metadata text,
	refresh_token_value text,
	refresh_token_issued_at timestamptz,
	refresh_token_expires_at timestamptz,
	refresh_token_metadata text,
	CONSTRAINT oauth2_authorization_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE public.oauth2_authorization OWNER TO postgres;
-- ddl-end --

-- object: public.oauth2_authorization_consent | type: TABLE --
-- DROP TABLE IF EXISTS public.oauth2_authorization_consent CASCADE;
CREATE TABLE public.oauth2_authorization_consent (
	registered_client_id varchar(100) NOT NULL,
	principal_name varchar(200) NOT NULL,
	authorities varchar(1000) NOT NULL,
	CONSTRAINT oauth2_authorization_consent_pk PRIMARY KEY (registered_client_id,principal_name)
);
-- ddl-end --
ALTER TABLE public.oauth2_authorization_consent OWNER TO postgres;
-- ddl-end --

-- object: oauth2_authorization_consent_fk | type: CONSTRAINT --
-- ALTER TABLE public.oauth2_authorization DROP CONSTRAINT IF EXISTS oauth2_authorization_consent_fk CASCADE;
ALTER TABLE public.oauth2_authorization ADD CONSTRAINT oauth2_authorization_consent_fk FOREIGN KEY (registered_client_id,principal_name)
REFERENCES public.oauth2_authorization_consent (registered_client_id,principal_name) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.oauth2_registered_client | type: TABLE --
-- DROP TABLE IF EXISTS public.oauth2_registered_client CASCADE;
CREATE TABLE public.oauth2_registered_client (
	id varchar(100) NOT NULL,
	client_id varchar(100) NOT NULL,
	client_id_issued_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	client_secret varchar(200),
	client_secret_expires_at timestamp,
	client_name varchar(200) NOT NULL,
	client_authentication_methods varchar(1000) NOT NULL,
	authorization_grant_types varchar(1000) NOT NULL,
	redirect_uris varchar,
	scopes varchar(1000) NOT NULL,
	client_settings varchar(2000) NOT NULL,
	token_settings varchar(2000) NOT NULL,
	CONSTRAINT oauth2_registered_client_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE public.oauth2_registered_client OWNER TO postgres;
-- ddl-end --


