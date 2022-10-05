\c client5
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

ALTER TABLE VHashMatrix OWNER TO client5;
ALTER TABLE DiUnitRange OWNER TO client5;
ALTER TABLE HashList OWNER TO client5;

\c client6
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

ALTER TABLE VHashMatrix OWNER TO client6;
ALTER TABLE DiUnitRange OWNER TO client6;
ALTER TABLE HashList OWNER TO client6;

