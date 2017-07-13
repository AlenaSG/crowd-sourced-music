--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: decades; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE decades (
    id integer NOT NULL,
    name character varying,
    image character varying
);


ALTER TABLE decades OWNER TO "Guest";

--
-- Name: decades_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE decades_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE decades_id_seq OWNER TO "Guest";

--
-- Name: decades_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE decades_id_seq OWNED BY decades.id;


--
-- Name: songs; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE songs (
    id integer NOT NULL,
    name character varying,
    decadeid integer
);


ALTER TABLE songs OWNER TO "Guest";

--
-- Name: songs_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE songs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE songs_id_seq OWNER TO "Guest";

--
-- Name: songs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE songs_id_seq OWNED BY songs.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY decades ALTER COLUMN id SET DEFAULT nextval('decades_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY songs ALTER COLUMN id SET DEFAULT nextval('songs_id_seq'::regclass);


--
-- Data for Name: decades; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY decades (id, name, image) FROM stdin;
5	90s	https://www.thatsmytop10.com/wp-content/uploads/2015/06/Top-10-Nostalgic-Indi-Pop-songs-from-the-90s.jpg
6	70s	http://p7cdn4static.sharpschool.com/UserFiles/Servers/Server_326868/Image/Alumni/slang-1970s.png
7	80's	https://i.ytimg.com/vi/4w3zE9bK-D4/maxresdefault.jpg
\.


--
-- Name: decades_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('decades_id_seq', 7, true);


--
-- Data for Name: songs; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY songs (id, name, decadeid) FROM stdin;
1	Vogue	1
2	Ice Ice Baby	1
3	Girls Just Wanna Have Fun	2
4	Thriller	2
7	Billie Jean	2
8	Heart of Glass	3
9	Dancing Queen	3
10	American Pie	3
11	Play That Funky Music	3
12	Hot Stuff	3
13	Le Freak	3
14	Bohemian Rhapsody	3
15	My Sharona	3
16	Killing Me Softly	3
17	Imagine	3
18	Take On Me	2
19	I Wanna Dance With Somebody	2
20	Like a Prayer	2
21	Call Me	2
22	Every Breath You Take	2
23	You're the Inspiration	2
24	Down Under	2
25	Nothing Compares to You	1
26	I Will Always Love You	1
27	Don't Speak	1
28	End of the Road	1
29	Baby One More Time	1
30	Livin' la Vida Loca	1
31	Gangsta's Paradise	1
32	Macarena	1
33	I Will Always Love You	5
34	Nothing Compares to You	5
35	Baby One More Time	5
36	Macarena	5
37	Don't Speak	5
38	Livin' la Vida Loca	5
39	Gangsta's Paradise	5
40	Ice Ice Baby	5
41	Vogue	5
42	My Name Is	5
43	Billie Jean	7
44	Take On Me	7
45	Like a Prayer	7
46	I Wanna Dance With Somebody	7
47	Every Breath You Take	7
48	You're the Inspiration	7
49	Beat It	7
50	Sweet Dreams	7
51	I Love Rock and Roll	7
52	I Just Called to Say I Love You	7
53	American Pie	6
54	Dancing Queen	6
55	Hot Stuff	6
56	Le Freak	6
57	I Will Survive	6
58	Heart of Glass	6
59	Play That Funky Music	6
60	Killing Me Softly	6
61	Bohemian Rhapsody	6
62	Staying Alive	6
\.


--
-- Name: songs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('songs_id_seq', 62, true);


--
-- Name: decades_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY decades
    ADD CONSTRAINT decades_pkey PRIMARY KEY (id);


--
-- Name: songs_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY songs
    ADD CONSTRAINT songs_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

