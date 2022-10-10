\c peer3
DROP TABLE V_PERSON_DATA;
DROP TABLE IF EXISTS person_rc;
DROP TABLE IF EXISTS PERSON_STATS;
DROP TABLE IF EXISTS HashList;
CREATE TABLE V_PERSON_DATA (
                               data_id UUID PRIMARY KEY,
                               client_id VARCHAR ( 50 ) NOT NULL,
                               vi text[],
                               verified boolean,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               batch_time VARCHAR ( 50 )
);
CREATE TABLE person_rc (
                           rc_id UUID PRIMARY KEY,
                           user_id UUID,
                           row integer,
                           col integer,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           batch_time VARCHAR ( 50 )
);
CREATE TABLE PERSON_STATS (
                              user_id UUID PRIMARY KEY,
                              client_name TEXT,
                              count integer,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              batch_time VARCHAR ( 50 )
);

CREATE TABLE HashList (
                          hash_id UUID PRIMARY KEY,
                          client_id UUID NOT NULL,
                          rowOrCol TEXT,
                          index integer,
                          HashResult Integer,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          batch_time VARCHAR ( 50 )
);
ALTER TABLE HashList OWNER TO peer3;
ALTER TABLE V_PERSON_DATA OWNER TO peer3;
ALTER TABLE person_rc OWNER TO peer3;
ALTER TABLE PERSON_STATS OWNER TO peer3;



\c client3
DROP TABLE IF EXISTS VHashMatrix;
DROP TABLE IF EXISTS DiUnitRange;
DROP TABLE IF EXISTS HashList;

CREATE TABLE VHashMatrix (
                             v_id UUID PRIMARY KEY,
                             row text,
                             col text,
                             vi text[],
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             batch_time VARCHAR ( 50 )
);
CREATE TABLE DiUnitRange (
                             d_id serial PRIMARY KEY,
                             unitrange TEXT,
                             di TEXT[],
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             batch_time VARCHAR ( 50 )
);
CREATE TABLE HashList (
                          hash_id UUID PRIMARY KEY,
                          rowOrCol TEXT,
                          index integer,
                          concatedResult TEXT,
                          HashResult Integer,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          batch_time VARCHAR ( 50 )
);

ALTER TABLE VHashMatrix OWNER TO client3;
ALTER TABLE DiUnitRange OWNER TO client3;
ALTER TABLE HashList OWNER TO client3;

\c client4
DROP TABLE IF EXISTS VHashMatrix;
DROP TABLE IF EXISTS DiUnitRange;
DROP TABLE IF EXISTS HashList;

CREATE TABLE VHashMatrix (
                             v_id UUID PRIMARY KEY,
                             row text,
                             col text,
                             vi text[],
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             batch_time VARCHAR ( 50 )
);
CREATE TABLE DiUnitRange (
                             d_id serial PRIMARY KEY,
                             unitrange TEXT,
                             di TEXT[],
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             batch_time VARCHAR ( 50 )
);
CREATE TABLE HashList (
                          hash_id UUID PRIMARY KEY,
                          rowOrCol TEXT,
                          index integer,
                          concatedResult TEXT,
                          HashResult Integer,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          batch_time VARCHAR ( 50 )
);

ALTER TABLE VHashMatrix OWNER TO client4;
ALTER TABLE DiUnitRange OWNER TO client4;
ALTER TABLE HashList OWNER TO client4;

