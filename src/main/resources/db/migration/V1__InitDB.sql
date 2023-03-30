--Table financial_report_data
--------------------------------------------------------------------
create table financial_report_data
(
    id               serial
        constraint financial_report_data_pkey
            primary key,
    num_working_days integer not null,
    to1_cost         integer not null,
    to2_cost         integer not null,
    kr_cost          integer not null
);

alter table financial_report_data
    owner to postgres;
--------------------------------------------------------------------

--Table drivers
--------------------------------------------------------------------
create table drivers
(
    id              serial
        constraint drivers_pkey
            primary key,
    last_name       varchar not null,
    first_name      varchar not null,
    middle_name     varchar not null,
    driving_license varchar not null
        constraint drivers_driving_license_key
            unique
);

alter table drivers
    owner to postgres;
--------------------------------------------------------------------

create table if not exists correcting_data (
    id serial constraint correcting_data_pkey primary key,
    city_type varchar not null,
    climate_type varchar not null,
    relief_type varchar not null,
    road_type varchar not null
);
alter table correcting_data
    owner to postgres;

INSERT INTO correcting_data VALUES (1, 'SMALL', 'TEMPERATE', 'R1', 'D1');

--Table cars
--------------------------------------------------------------------
create table cars
(
    id               serial
        constraint cars_pkey
            primary key,
    brand            varchar        not null,
    model            varchar        not null,
    year             numeric(4)     not null,
    kilometrage      numeric(11, 3) not null,
    license_plate    varchar        not null
        constraint cars_license_plate_key
            unique,
    normative_to1    numeric(5)     not null,
    normative_to2    numeric(5)     not null,
    normative_kr    numeric(8)     not null,
    fact_to1    numeric(8, 3)     not null,
    fact_to2    numeric(8, 3)     not null,
    fact_kr    numeric(11, 3)     not null,
    km_before_to1 numeric(8, 3)     not null,
    km_before_to2 numeric(8, 3)     not null,
    km_before_kr       numeric(11, 3)     not null,
    latitude         numeric(9, 6)  not null,
    longitude        numeric(9, 6)  not null,
    avg_kilometrage numeric(8, 3),
    driver_id        integer
        constraint cars_driver_id_key
            unique
        constraint cars_driver_id_fkey
            references drivers
            on delete restrict
);

alter table cars
    owner to postgres;
--------------------------------------------------------------------



--Table history
--------------------------------------------------------------------
create table history
(
    id          serial
        constraint history_pkey
            primary key,
    car_id      integer       not null
        constraint history_car_id_fkey
            references cars
            on delete cascade,
    h_date      date          not null,
    kilometrage numeric(7, 3) not null
);

alter table history
    owner to postgres;
--------------------------------------------------------------------




--Table log
--------------------------------------------------------------------
create table log
(
    id          serial
        constraint log_pkey
            primary key,
    car_id      integer                             not null
        constraint log_car_id_fkey
            references cars
            on delete cascade,
    latitude    numeric(9, 6)                       not null,
    longitude   numeric(9, 6)                       not null,
    log_time    timestamp default CURRENT_TIMESTAMP not null,
    kilometrage numeric(7, 3)
);

alter table log
    owner to postgres;
--------------------------------------------------------------------



--Table users
--------------------------------------------------------------------
create table users
(
    id       serial
        constraint users_pkey
            primary key,
    login    varchar not null
        constraint users_login_key
            unique,
    password varchar not null
);

alter table users
    owner to postgres;
--------------------------------------------------------------------




--Table roles
--------------------------------------------------------------------
create table roles
(
    id   serial
        constraint roles_pkey
            primary key,
    name varchar not null
        constraint roles_name_key
            unique
);

alter table roles
    owner to postgres;
--------------------------------------------------------------------




--Table user_roles
--------------------------------------------------------------------
create table user_roles
(
    user_id integer not null
        constraint user_roles_user_id_fkey
            references users,
    role_id integer not null
        constraint user_roles_role_id_fkey
            references roles,
    constraint user_roles_user_id_role_id_key
        unique (user_id, role_id)
);

alter table user_roles
    owner to postgres;
--------------------------------------------------------------------




--Function clear_log
--------------------------------------------------------------------
create function clear_log() returns void
    language plpgsql
as
$$
BEGIN

    INSERT INTO history (car_id, h_date, kilometrage)
    SELECT car_id, CAST(log_time AS date), SUM(kilometrage) FROM log GROUP BY(car_id, CAST(log_time AS date));

    DELETE FROM log;

    INSERT INTO log (car_id, latitude, longitude, kilometrage)
    SELECT id, latitude, longitude, 0.0 FROM cars;
    RETURN;
END;
$$;

alter function clear_log() owner to postgres;
--------------------------------------------------------------------




--Function get_distance
--------------------------------------------------------------------
create function get_distance(lat1 numeric, long1 numeric, lat2 numeric, long2 numeric) returns numeric
    language plpgsql
as
$$
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

alter function get_distance(numeric, numeric, numeric, numeric) owner to postgres;
--------------------------------------------------------------------




--Trigger insert_car
--------------------------------------------------------------------
create function insert_car() returns trigger
    language plpgsql
as
$$
BEGIN

    INSERT INTO log (car_id, latitude, longitude, kilometrage)
    VALUES (NEW.id, NEW.latitude, NEW.longitude, 0);
    RETURN NEW;

END;
$$;

alter function insert_car() owner to postgres;
--------------------------------------------------------------------



--Trigger update_position
--------------------------------------------------------------------
create function update_position() returns trigger
    language plpgsql
as
$$
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
                    km_before_to1 = OLD.km_before_to1 - distance,
                    km_before_to2 = OLD.km_before_to2 - distance,
                    km_before_kr = OLD.km_before_kr - distance
    WHERE id = OLD.id;

    RETURN NEW;
END;
$$;

alter function update_position() owner to postgres;
--------------------------------------------------------------------

CREATE TRIGGER insert_new_car AFTER INSERT ON public.cars FOR EACH ROW EXECUTE FUNCTION public.insert_car();
CREATE TRIGGER update_car_position AFTER UPDATE OF latitude, longitude ON public.cars FOR EACH ROW EXECUTE FUNCTION public.update_position();


INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_DISPATCHER');
INSERT INTO roles(name) VALUES ('ROLE_DEVICE');

INSERT INTO users(login, password) VALUES ('admin', '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');
INSERT INTO user_roles(user_id, role_id) VALUES (1,1);

INSERT INTO users(login, password) VALUES ('device', '$2a$10$tjbH0B5f5Yy7DzfnxS1W2uW1RfVB8ZVmQPcGSlYYa5GJeDZt4LJ6y');
INSERT INTO user_roles(user_id, role_id) VALUES (2,3);

INSERT INTO users(login, password) VALUES ('dispatcher', '$2a$10$6e60Gr./BAImN4zwKmzp8ujAezYlfTxq0X3zeRSIurN4Cg.zKN0vq');
INSERT INTO user_roles(user_id, role_id) VALUES (3,2);

INSERT INTO financial_report_data(num_working_days, to1_cost, to2_cost, kr_cost) VALUES (365, 10000, 20000, 150000);

INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Беляев', 'Артем', 'Александрович', '18 01 365123');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Повов', 'Платон', 'Егорович', '30 15 930476');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Чернышев', 'Роман', 'Иванович', '56 07 303057');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Никифоров', 'Георгий', 'Михайлович', '57 48 463210');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Потапов', 'Глеб', 'Владиславович', '24 07 024930');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Галкин', 'Владислав', 'Дмитриевич', '75 36 793625');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Богданов', 'Тимур', 'Николаевич', '54 69 316497');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Гущин', 'Владимир', 'Михайлович', '59 64 829173');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Воробьев', 'Сергей', 'Сергеевич', '64 54 813232');
INSERT INTO drivers(last_name, first_name, middle_name, driving_license) VALUES ('Панин', 'Никита', 'Данилович', '58 70 603289');

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2115', 2010, 50000, 'X111PE152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.21990417154902, 43.937407852453845, 120, 1);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2114', 2012, 80000, 'E378AE152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.316663891500895, 43.98327933667687, 85, 2);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2107', 2012, 35000, 'A777AA152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.257559444121654, 44.00961905427317, 53, 3);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ГАЗ', '2410', 2000, 47000, 'P410AT152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.352712, 44.057927, 75, 4);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ГАЗ', '3102', 2001, 87000, 'O689MT152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.288791, 43.892313, 91, 5);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2121', 2010, 95000, 'B903MX152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.309760, 43.768631, 153, 6);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2170', 2013, 55300, 'B430XE152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.200315, 43.890134, 250, 7);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2170', 2011, 88500, 'C505OC152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.243411, 44.085328, 38, 8);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2113', 2010, 112000, 'X399YY152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.299817, 44.066605, 57, 9);

INSERT INTO cars(brand, model, year, kilometrage, license_plate, normative_to1, normative_to2, normative_kr, fact_to1, fact_to2, fact_kr, km_before_to1, km_before_to2, km_before_kr, latitude, longitude, avg_kilometrage, driver_id) VALUES
    ('ВАЗ', '2110', 2005, 81500, 'B302OP152', 8000, 16000, 200000, 8000, 16000, 200000, 8000, 16000, 200000, 56.339637, 43.924463, 49, 10);