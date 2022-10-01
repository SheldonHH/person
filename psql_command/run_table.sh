psql -U postgres < ./CREATE_ROLE_AND_DB_FLYWAY_SCHEMA.sql
psql -U postgres < ./table_creation_1csp.sql
psql -U postgres < ./table_creation_2.sql
psql -U postgres < ./table_creation_3_4_client.sql
psql -U postgres < ./table_creation_5_6_client.sql

#psql -U postgres > ./table_creation_1csp.sql