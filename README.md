# Training Device

## 1. Create Environment
java.version: 17
### 1.1 create psql docker and mount local directory
```bash
docker run --name postgres-spring -v {PATH}/:/root/training1 -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine`
```

### 1.2 Create Database, Roles, Tables
- [psql_command/run_table.sh](psql_command/run_table.sh)
    - [CREATE_ROLE_AND_DB_FLYWAY_SCHEMA.sql](psql_command/CREATE_ROLE_AND_DB_FLYWAY_SCHEMA.sh)  create database, role and `alter` owner role to respective database; `assign` owner to `flyway_schema_history`
    - [table_creation_1csp.sql](psql_command/table_creation_1csp.sh) create table for `client1`, `server1`, `peer1`

API Layer / Controller Layer
Service Layer
Data Access Layer

## 2. PreSQL Commands

#### 2.1 Restart
```bash
# kill existing postgresql daemon
# sudo kill -9 $(sudo lsof -t -i:5432)
docker stop  $(docker ps -a | grep -E 'postgres-spring' | awk '{print $1}' | awk 'NR==1') && docker rm  $(docker ps -a | grep -E 'postgres-spring' | awk '{print $1}' | awk 'NR==1')
docker run --name postgres-spring -v /Users/mac/singapore/person1:/root/person1 -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
docker exec -w /root/person1 -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash
# Execute `run_table.sh`
cd psql_command
./run_table.sh
```
##### Issue: `docker: Error response from daemon: Ports are not available: listen tcp 0.0.0.0:5432: bind: address already in use.`
```bash
sudo kill -9 $(sudo lsof -t -i:5432)
```


#### 2.2 Enter DB CommandLine
```sql
psql -U postgres
docker port demodb
```


## 3. Build and Deploy `.jar` AFTER PERSISTANCE CREATION
```bash
mvn clean package
java -jar trainingNode1-0.0.1-SNAPSHOT.jar
```


## 5. Issues and Solutions

### Issue 1:

org.springframework.beans.factory.BeanCreationException:
Error creating bean with name 'flywayInitializer' defined in class path resource
[org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Invocation of init method failed;
nested exception is java.lang.RuntimeException:
Failed to get driver instance for `jdbcUrl=jdbc:postgresql://localhost:5432/spring-boot-postgres-db/demodb`


#### Issue 2:

Issue docker: Error response from daemon: Ports are not available: listen tcp 0.0.0.0:5432: bind: address already in use.
Soultion: `sudo lsof -i tcp:5432`
Restart
```bash
docker run  -d --name openjdk -it openjdk /bin/bash
docker exec -it $(docker ps | grep -E 'openjdk' | awk '{print $1}') /bin/bash
\l
```
```bash
lsof -i:5432 | awk 'NR==2 {print $2}'
```




JDBC: Java Database Connectivity (JDBC)
flywaydb: applying new migrations



## 6. Explanation

### 6.1 PostgreSQL
In general, PostgreSQL is best suited for systems that require execution of complex queries, or data warehousing and data analysis

### 6.2. Flyway Schema History Table
**Flyway updates a database from one version to the next using migrations**
Flyway uses a schema history table to track the version of each database, recording in it every versioned migration file applied to build that version.   
To achieve version control, Flyway needs to keep all the information required within the database, in table `flyway_schema_history` for bookkeeping.
**Flyway always puts this table in the default schema**. If there is only one schema in the database, it is easy to work out which is the default schema.
If you have more than one schema, you need to provide a list of them to Flyway as a schemas parameter. Flyway assumes that the default schema is the first one in the list unless you explicitly specify otherwise by setting the `defaultSchema` parameter.

##### 6.2.1 `metatdata` `migration`
When Flyway is presented with a database, it tries to locate its `metadata` table. If it does not exist, it will create one and then searches for `migration` files in the file location (or list of file locations) that you specify.
You can write these `migration` files in either SQL or Java.

Flyway sorts files based on version number and applies to the target database, in order. It stores, it its history table, the version number, description, type (SQL or Java) and filename, together with a checksum.

##### 6.2.2 Apply Migration File
Once applies a migration file successfully, it also records in the table the date, the time it took to run and who ran the migration. Running migration trigger to **recalculates the checksum for each file** that has already been applied to the database and will raise an error if it has been changed subsequently.


Flyway always puts the `flyway_schema_history` table in the default schema.   
On start-up, Flyway sees whether you have specified what schema should be the default schema, which you do using the `flyway.defaultSchema` setting.   
If you **haven’t** specified a default schema, Flyway uses the first schema in the list you provide in flyway.schemas.

If Flyway is asked to clean a database of all its contents, it removes the contents of each of the schemas, in the order of the `flyway.schemas` list and them creates and fills a fresh `flyway_schema_history` table.



#### 6.2.3. Flyway Schema History Table
Flyway tracks the version of each database since it knows exactly which versioned migration files were applied to build each version. Once applied, it won’t let you alter them subsequently. To do this, it needs to keep all the information required within the database, in a bookkeeping table called the flyway_schema_history table. Flyway always puts this table in the default schema. If there is only one schema in the database, it is easy to work out which is the default schema. Where the RDBMS has a construct of a ‘default schema’, Flyway can use that. If you have more than one schema, you need to provide a list of them to Flyway as a schemas parameter. Flyway assumes that the default schema is the first one in the list unless you explicitly specify otherwise by setting the defaultSchema parameter.

When Flyway is presented with a database, it tries to locate its metadata table. If it does not exist, it will create one and then searches for migration files in the file location (or list of file locations) that you specify. You can write these migration files in either SQL or Java.

When Flyway has located all the files, it sorts them based on their version number and applies them to the target database, in order. It stores, it its history table, the version number, description, type (SQL or Java) and filename, together with a checksum.

When Flyway applies a migration file successfully, it also records in the table the date, the time it took to run and who ran the migration. Each time it runs a migration, it recalculates the checksum for each file that has already been applied to the database and will raise an error if it has been changed subsequently.



Flyway is a version control application to evolve your Database schema easily and reliably across all your instances.
Flyway uses a schema history table to track the version of each database, recording in it every versioned migration file applied to build that version.
It's worth understanding exactly how Flyway uses this table, the possible dangers of moving it to a non-default location and how to do it safely, if required.

Flyway always puts the `flyway_schema_history` table in the default schema.
On start-up, Flyway sees whether you have specified what schema should be the default schema, which you do using the `flyway.defaultSchema` setting.
If you haven’t specified a default schema, Flyway uses the first schema in the list you provide in flyway.schemas.

If Flyway is asked to clean a database of all its contents, it removes the contents of each of the schemas, in the order of the `flyway.schemas` list and them creates and fills a fresh flyway_schema_history table.
If Flyway creates a schema, it will then drop the schema when performing the ‘clean’ action.

The name of the History table
You might, for some reason want to change the name of the schema history table from its default of `flyway_schema_history`. You can do this with the configuration item flyway.table.
