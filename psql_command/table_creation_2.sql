\c peer2
DROP TABLE V_PERSON_DATA;
DROP TABLE IF EXISTS person_rc;
DROP TABLE IF EXISTS PERSON_STATS;
DROP TABLE IF EXISTS HASHLIST;

DROP TABLE V_PERSON_DATA;
DROP TABLE IF EXISTS person_rc;
DROP TABLE IF EXISTS PERSON_STATS;
DROP TABLE IF EXISTS HashList;
CREATE TABLE V_PERSON_DATA (
                               data_id UUID PRIMARY KEY,
                               client_id VARCHAR ( 50 ),
                               vi text[],
                               verified boolean,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE person_rc (
                           rc_id UUID PRIMARY KEY,
                           user_id UUID,
                           row integer,
                           col integer,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE PERSON_STATS (
                              user_id UUID PRIMARY KEY,
                              client_name TEXT,
                              count integer,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE HashList (
                          hash_id UUID PRIMARY KEY,
                          client_id UUID NOT NULL,
                          rowOrCol TEXT,
                          index integer,
                          HashResult Integer,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE HashList OWNER TO peer2;
ALTER TABLE V_PERSON_DATA OWNER TO peer2;
ALTER TABLE person_rc OWNER TO peer2;
ALTER TABLE PERSON_STATS OWNER TO peer2;





\c server2
DROP TABLE IF EXISTS U_PERSON_DATA;
CREATE TABLE U_PERSON_DATA (
                               data_id UUID PRIMARY KEY,
                               client_id VARCHAR ( 50 ) ,
                               ui text[],
                               verified boolean,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE U_PERSON_DATA OWNER TO server1;
DROP TABLE IF EXISTS PERSON_SIGNATURE;
CREATE TABLE PERSON_SIGNATURE (
                                  person_id UUID PRIMARY KEY,
                                  client_name VARCHAR ( 50 ),
                                  signature text,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE PERSON_SIGNATURE OWNER TO server2;









\c client2
DROP TABLE IF EXISTS VHashMatrix;
DROP TABLE IF EXISTS DiUnitRange;
DROP TABLE IF EXISTS HashList;

CREATE TABLE VHashMatrix (
                             v_id UUID PRIMARY KEY,
                             row text,
                             col text,
                             vi text[]
);
CREATE TABLE DiUnitRange (
                             d_id serial PRIMARY KEY,
                             unitrange TEXT,
                             di TEXT[]
);
CREATE TABLE HashList (
                          hash_id UUID PRIMARY KEY,
                          rowOrCol TEXT,
                          index integer,
                          concatedResult TEXT,
                          HashResult Integer
);

ALTER TABLE VHashMatrix OWNER TO client2;
ALTER TABLE DiUnitRange OWNER TO client2;
ALTER TABLE HashList OWNER TO client2;



