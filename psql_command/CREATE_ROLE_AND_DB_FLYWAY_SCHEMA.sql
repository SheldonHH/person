CREATE ROLE server1;
--- DROP DATABASE if exists server1;
CREATE DATABASE server1 WITH ENCODING = 'UTF8' OWNER = server1;
--- DROP DATABASE if exists server2;
CREATE ROLE server2;
CREATE DATABASE server2 WITH ENCODING = 'UTF8' OWNER = server2;
-- CREATE ROLE server3;
-- CREATE DATABASE server3 WITH ENCODING = 'UTF8' OWNER = server3;

----------------------------------------------------------------------------------------------------
CREATE ROLE peer1;
--- DROP DATABASE if exists peer1;
CREATE DATABASE peer1 WITH ENCODING = 'UTF8' OWNER = peer1;
--- DROP DATABASE if exists peer2;
CREATE ROLE peer2;
CREATE DATABASE peer2 WITH ENCODING = 'UTF8' OWNER = peer2;

--- DROP DATABASE if exists client1;
CREATE ROLE client1;
CREATE DATABASE client1 WITH ENCODING = 'UTF8' OWNER = client1;
--- DROP DATABASE if exists client2;
CREATE ROLE client2;
CREATE DATABASE client2 WITH ENCODING = 'UTF8' OWNER = client2;
--- DROP DATABASE if exists client3;
CREATE ROLE client3;
CREATE DATABASE client3 WITH ENCODING = 'UTF8' OWNER = client3;
--- DROP DATABASE if exists client4;
CREATE ROLE client4;
CREATE DATABASE client4 WITH ENCODING = 'UTF8' OWNER = client4;
--- DROP DATABASE if exists client5;
CREATE ROLE client5;
CREATE DATABASE client5 WITH ENCODING = 'UTF8' OWNER = client5;
--- DROP DATABASE if exists client6;
CREATE ROLE client6;
CREATE DATABASE client6 WITH ENCODING = 'UTF8' OWNER = client6;
----------------------------------------------------------------------------------------------------





ALTER ROLE "server1" WITH LOGIN;
ALTER ROLE "server2" WITH LOGIN;
-- ALTER ROLE "server3" WITH LOGIN;
ALTER ROLE "peer1" WITH LOGIN;
ALTER ROLE "peer2" WITH LOGIN;
ALTER ROLE "client1" WITH LOGIN;
ALTER ROLE "client2" WITH LOGIN;
ALTER ROLE "client3" WITH LOGIN;
ALTER ROLE "client4" WITH LOGIN;
ALTER ROLE "client5" WITH LOGIN;
ALTER ROLE "client6" WITH LOGIN;

ALTER ROLE server1 WITH PASSWORD 'password';
ALTER ROLE server2 WITH PASSWORD 'password';
-- ALTER ROLE server3 WITH PASSWORD 'password';
ALTER ROLE peer1 WITH PASSWORD 'password';
ALTER ROLE peer2 WITH PASSWORD 'password';
ALTER ROLE client1 WITH PASSWORD 'password';
ALTER ROLE client2 WITH PASSWORD 'password';
ALTER ROLE client3 WITH PASSWORD 'password';
ALTER ROLE client4 WITH PASSWORD 'password';
ALTER ROLE client5 WITH PASSWORD 'password';
ALTER ROLE client6 WITH PASSWORD 'password';

\c server1
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO server1;
\c server2
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO server2;
-- \c server3
-- CREATE TABLE public.flyway_schema_history (
--                                               installed_rank integer NOT NULL,
--                                               version character varying(50),
--                                               description character varying(200) NOT NULL,
--                                               type character varying(20) NOT NULL,
--                                               script character varying(1000) NOT NULL,
--                                               checksum integer,
--                                               installed_by character varying(100) NOT NULL,
--                                               installed_on timestamp without time zone DEFAULT now() NOT NULL,
--                                               execution_time integer NOT NULL,
--                                               success boolean NOT NULL
-- );
-- ALTER TABLE public.flyway_schema_history OWNER TO server3;


\c peer1
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO peer1;
\c peer2
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO peer2;


\c client1
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client1;
\c client2
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client2;
\c client3
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client3;
\c client4
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client4;


\c client5
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client5;
\c client6
CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);
ALTER TABLE public.flyway_schema_history OWNER TO client6;