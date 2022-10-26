-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.0-beta
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: Thirumal

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: "authorization-resource-server" | type: DATABASE --
-- DROP DATABASE IF EXISTS "authorization-resource-server";
CREATE DATABASE "authorization-resource-server";
-- ddl-end --
COMMENT ON DATABASE "authorization-resource-server" IS E'Created Thirumal';
-- ddl-end --


-- object: public.oauth2_authorization | type: TABLE --
-- DROP TABLE IF EXISTS public.oauth2_authorization CASCADE;
CREATE TABLE public.oauth2_authorization (
	id serial NOT NULL,
	registered_client_id varchar(100) NOT NULL,
	principal_name varchar(200) NOT NULL,
	authorization_grant_type varchar(100) NOT NULL,
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


