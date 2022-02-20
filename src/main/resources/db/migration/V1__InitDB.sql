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

CREATE SEQUENCE public.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.cars_id_seq OWNER TO postgres;

ALTER SEQUENCE public.cars_id_seq OWNED BY public.cars.id;

CREATE TABLE public.drivers (
    id integer NOT NULL,
    last_name character varying NOT NULL,
    first_name character varying NOT NULL,
    middle_name character varying NOT NULL,
    driving_license character varying NOT NULL
);

ALTER TABLE public.drivers OWNER TO postgres;


CREATE SEQUENCE public.drivers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.drivers_id_seq OWNER TO postgres;

ALTER SEQUENCE public.drivers_id_seq OWNED BY public.drivers.id;


CREATE TABLE public.history (
    id integer NOT NULL,
    car_id integer NOT NULL,
    h_date date NOT NULL,
    kilometrage numeric(7,3) NOT NULL
);

ALTER TABLE public.history OWNER TO postgres;


CREATE SEQUENCE public.history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.history_id_seq OWNER TO postgres;

ALTER SEQUENCE public.history_id_seq OWNED BY public.history.id;

CREATE TABLE public.log (
    id integer NOT NULL,
    car_id integer NOT NULL,
    latitude numeric(9,6) NOT NULL,
    longitude numeric(9,6) NOT NULL,
    log_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    kilometrage numeric(7,3)
);


ALTER TABLE public.log OWNER TO postgres;


CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;


ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;


ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


CREATE TABLE public.user_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying NOT NULL,
    password character varying NOT NULL
);

ALTER TABLE public.users OWNER TO postgres;


CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


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

ALTER TABLE ONLY public.cars ALTER COLUMN id SET DEFAULT nextval('public.cars_id_seq'::regclass);

ALTER TABLE ONLY public.drivers ALTER COLUMN id SET DEFAULT nextval('public.drivers_id_seq'::regclass);

ALTER TABLE ONLY public.history ALTER COLUMN id SET DEFAULT nextval('public.history_id_seq'::regclass);

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_driver_id_key UNIQUE (driver_id);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_license_plate_key UNIQUE (license_plate);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.drivers
    ADD CONSTRAINT drivers_driving_license_key UNIQUE (driving_license);

ALTER TABLE ONLY public.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_role_id_key UNIQUE (user_id, role_id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

CREATE TRIGGER insert_new_car AFTER INSERT ON public.cars FOR EACH ROW EXECUTE FUNCTION public.insert_car();

CREATE TRIGGER update_car_position AFTER UPDATE OF latitude, longitude ON public.cars FOR EACH ROW EXECUTE FUNCTION public.update_position();

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES public.drivers(id) ON DELETE RESTRICT NOT VALID;

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE;

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);

INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_DISPATCHER');
INSERT INTO roles(name) VALUES ('ROLE_DEVICE');

INSERT INTO users(login, password) VALUES ('admin', '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');
INSERT INTO user_roles(user_id, role_id) VALUES (1,1);



