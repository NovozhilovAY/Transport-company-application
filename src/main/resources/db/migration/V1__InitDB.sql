--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2022-02-09 12:18:08

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

--
-- TOC entry 216 (class 1255 OID 73860)
-- Name: clear_log(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.clear_log() RETURNS void
    LANGUAGE plpgsql
AS $$
BEGIN

    INSERT INTO history (car_id, h_date, kilometrage)
    SELECT car_id, CAST(log_time AS date), SUM(kilometrage) FROM log GROUP BY(car_id, CAST(log_time AS date));

    DELETE FROM log;

    INSERT INTO log (car_id, latitude, longitude, kilometrage)
    SELECT id, latitude, longitude, 0.0 FROM cars;
    RETURN;
END;
$$;


ALTER FUNCTION public.clear_log() OWNER TO postgres;

--
-- TOC entry 213 (class 1255 OID 73855)
-- Name: get_distance(numeric, numeric, numeric, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_distance(lat1 numeric, long1 numeric, lat2 numeric, long2 numeric) RETURNS numeric
    LANGUAGE plpgsql
AS $$
DECLARE
    lt1 numeric;
    lg1 numeric;
    lt2 numeric;
    lg2 numeric;
BEGIN
    lt1 = radians(lat1);
    lt2 = radians(lat2);
    lg1 = radians(long1);
    lg2 = radians(long2);
    RETURN round(CAST((2 * 6371 * asin(sqrt(power(sin((lt2-lt1)/2),2) + cos(lt1) * cos(lt2) * power((lg2-lg1)/2,2))))AS numeric),3);
END;
$$;


ALTER FUNCTION public.get_distance(lat1 numeric, long1 numeric, lat2 numeric, long2 numeric) OWNER TO postgres;

--
-- TOC entry 214 (class 1255 OID 73856)
-- Name: insert_car(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insert_car() RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN

    INSERT INTO log (car_id, latitude, longitude, kilometrage)
    VALUES (NEW.id, NEW.latitude, NEW.longitude, 0);
    RETURN NEW;

END;
$$;


ALTER FUNCTION public.insert_car() OWNER TO postgres;

--
-- TOC entry 215 (class 1255 OID 73858)
-- Name: update_position(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_position() RETURNS trigger
    LANGUAGE plpgsql
AS $$
DECLARE
    distance numeric;

BEGIN

    IF OLD.latitude = NEW.latitude AND OLD.longitude = NEW.longitude THEN
        RETURN NEW;
    END IF;

    distance := get_distance(OLD.latitude, OLD.longitude, NEW.latitude, NEW.longitude);

    INSERT INTO log (car_id, latitude, longitude, kilometrage)
    VALUES (NEW.id, NEW.latitude, NEW.longitude, distance);

    UPDATE cars SET
                    kilometrage = OLD.kilometrage + distance,
                    km_before_maint = OLD.km_before_maint - distance
    WHERE id = OLD.id;

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_position() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 73764)
-- Name: cars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cars (
                             id integer NOT NULL,
                             brand character varying NOT NULL,
                             model character varying NOT NULL,
                             year numeric(4,0) NOT NULL,
                             kilometrage numeric(11,3) NOT NULL,
                             license_plate character varying NOT NULL,
                             maintenance_freq numeric(5,0) NOT NULL,
                             km_before_maint numeric(8,3) NOT NULL,
                             latitude numeric(9,6) NOT NULL,
                             longitude numeric(9,6) NOT NULL,
                             driver_id integer
);


ALTER TABLE public.cars OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 73762)
-- Name: cars_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cars_id_seq OWNER TO postgres;

--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 202
-- Name: cars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cars_id_seq OWNED BY public.cars.id;


--
-- TOC entry 201 (class 1259 OID 73751)
-- Name: drivers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.drivers (
                                id integer NOT NULL,
                                last_name character varying NOT NULL,
                                first_name character varying NOT NULL,
                                middle_name character varying NOT NULL,
                                driving_license character varying NOT NULL
);


ALTER TABLE public.drivers OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 73749)
-- Name: drivers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.drivers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.drivers_id_seq OWNER TO postgres;

--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 200
-- Name: drivers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.drivers_id_seq OWNED BY public.drivers.id;


--
-- TOC entry 207 (class 1259 OID 73798)
-- Name: history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.history (
                                id integer NOT NULL,
                                car_id integer NOT NULL,
                                h_date date NOT NULL,
                                kilometrage numeric(7,3) NOT NULL
);


ALTER TABLE public.history OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 73796)
-- Name: history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.history_id_seq OWNER TO postgres;

--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 206
-- Name: history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.history_id_seq OWNED BY public.history.id;


--
-- TOC entry 205 (class 1259 OID 73784)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
                            id integer NOT NULL,
                            car_id integer NOT NULL,
                            latitude numeric(9,6) NOT NULL,
                            longitude numeric(9,6) NOT NULL,
                            log_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                            kilometrage numeric(7,3)
);


ALTER TABLE public.log OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 73782)
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;

--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 204
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


--
-- TOC entry 211 (class 1259 OID 73824)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
                              id integer NOT NULL,
                              name character varying NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 73822)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 210
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 212 (class 1259 OID 73840)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
                                   user_id integer NOT NULL,
                                   role_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 73811)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              id integer NOT NULL,
                              login character varying NOT NULL,
                              password character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 73809)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 208
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2893 (class 2604 OID 73767)
-- Name: cars id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars ALTER COLUMN id SET DEFAULT nextval('public.cars_id_seq'::regclass);


--
-- TOC entry 2892 (class 2604 OID 73754)
-- Name: drivers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drivers ALTER COLUMN id SET DEFAULT nextval('public.drivers_id_seq'::regclass);


--
-- TOC entry 2896 (class 2604 OID 73801)
-- Name: history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history ALTER COLUMN id SET DEFAULT nextval('public.history_id_seq'::regclass);


--
-- TOC entry 2894 (class 2604 OID 73787)
-- Name: log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- TOC entry 2898 (class 2604 OID 73827)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2897 (class 2604 OID 73814)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2904 (class 2606 OID 73776)
-- Name: cars cars_driver_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_driver_id_key UNIQUE (driver_id);


--
-- TOC entry 2906 (class 2606 OID 73774)
-- Name: cars cars_license_plate_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_license_plate_key UNIQUE (license_plate);


--
-- TOC entry 2908 (class 2606 OID 73772)
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


--
-- TOC entry 2900 (class 2606 OID 73761)
-- Name: drivers drivers_driving_license_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drivers
    ADD CONSTRAINT drivers_driving_license_key UNIQUE (driving_license);


--
-- TOC entry 2902 (class 2606 OID 73759)
-- Name: drivers drivers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);


--
-- TOC entry 2912 (class 2606 OID 73803)
-- Name: history history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_pkey PRIMARY KEY (id);


--
-- TOC entry 2910 (class 2606 OID 73790)
-- Name: log log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- TOC entry 2918 (class 2606 OID 73834)
-- Name: roles roles_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);


--
-- TOC entry 2920 (class 2606 OID 73832)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2922 (class 2606 OID 73844)
-- Name: user_roles user_roles_user_id_role_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_role_id_key UNIQUE (user_id, role_id);


--
-- TOC entry 2914 (class 2606 OID 73821)
-- Name: users users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- TOC entry 2916 (class 2606 OID 73819)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2928 (class 2620 OID 73857)
-- Name: cars insert_new_car; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_new_car AFTER INSERT ON public.cars FOR EACH ROW EXECUTE FUNCTION public.insert_car();


--
-- TOC entry 2929 (class 2620 OID 73859)
-- Name: cars update_car_position; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_car_position AFTER UPDATE OF latitude, longitude ON public.cars FOR EACH ROW EXECUTE FUNCTION public.update_position();


--
-- TOC entry 2923 (class 2606 OID 73835)
-- Name: cars cars_driver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES public.drivers(id) ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 2925 (class 2606 OID 73804)
-- Name: history history_car_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE;


--
-- TOC entry 2924 (class 2606 OID 73791)
-- Name: log log_car_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE;


--
-- TOC entry 2927 (class 2606 OID 73850)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2926 (class 2606 OID 73845)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2022-02-09 12:18:08

--
-- PostgreSQL database dump complete
--

