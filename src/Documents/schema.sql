--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

-- Create user usuario with pass 1234

CREATE USER usuario WITH PASSWORD '1234';

-- Create database

CREATE DATABASE colectoredition;

-- Grant all privileges to usuario in colectoredition bbdd

GRANT ALL PRIVILEGES ON DATABASE colectoredition TO usuario;

-- Open bbdd colectoredition
\c colectoredition

-- Other Settings imported

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: plataforma; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.plataforma (
    idplataforma smallint NOT NULL,
    nombreplataforma character varying(20)
);


ALTER TABLE public.plataforma OWNER TO usuario;

--
-- Name: videojuego; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.videojuego (
    nombre text,
    precio character varying(15),
    tipocompra character varying(20),
    imagen text,
    idplataforma smallint
);


ALTER TABLE public.videojuego OWNER TO usuario;

--
-- Data for Name: plataforma; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.plataforma (idplataforma, nombreplataforma) FROM stdin;
\.


--
-- Data for Name: videojuego; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.videojuego (nombre, precio, tipocompra, imagen, idplataforma) FROM stdin;
\.


--
-- Name: plataforma plataforma_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.plataforma
    ADD CONSTRAINT plataforma_pkey PRIMARY KEY (idplataforma);


--
-- Name: videojuego videojuego_idplataforma_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.videojuego
    ADD CONSTRAINT videojuego_idplataforma_fkey FOREIGN KEY (idplataforma) REFERENCES public.plataforma(idplataforma);


--
-- PostgreSQL database dump complete
--

-- Exit bbdd

\c postgres

-- Re-open colectoredition bbdd

\c colectoredition
