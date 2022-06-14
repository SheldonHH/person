# Training Device

## 1. Installation
### 1.1 create psql docker and mount local directory
```bash
docker run --name postgres-spring -v {PATH}/:/root/training1 -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine`
```


### 1.2 Create Database, Roles, Tables
- [psql_command/run_table.sh](psql_command/run_table.sh) 
   - [CREATE_ROLE_AND_DB_FLYWAY_SCHEMA.sql](psql_command/CREATE_ROLE_AND_DB_FLYWAY_SCHEMA.sh)  create database, role and `alter` owner role to respective database; `assign` owner to `flyway_schema_history`
   - [table_creation_1csp.sql](psql_command/table_creation_1csp.sh) create table for `client1`, `server1`, `peer1`

## 2. Flyway Schema History Table
**Flyway updates a database from one version to the next using migrations**
Flyway uses a schema history table to track the version of each database, recording in it every versioned migration file applied to build that version.   
To achieve version control, Flyway needs to keep all the information required within the database, in table `flyway_schema_history` for bookkeeping.
**Flyway always puts this table in the default schema**. If there is only one schema in the database, it is easy to work out which is the default schema.
If you have more than one schema, you need to provide a list of them to Flyway as a schemas parameter. Flyway assumes that the default schema is the first one in the list unless you explicitly specify otherwise by setting the `defaultSchema` parameter.

#### 2.1 `metatdata` `migration`
When Flyway is presented with a database, it tries to locate its `metadata` table. If it does not exist, it will create one and then searches for `migration` files in the file location (or list of file locations) that you specify.
You can write these `migration` files in either SQL or Java.

Flyway sorts files based on version number and applies to the target database, in order. It stores, it its history table, the version number, description, type (SQL or Java) and filename, together with a checksum.

#### 2.2 Apply Migration File
Once applies a migration file successfully, it also records in the table the date, the time it took to run and who ran the migration. Running migration trigger to **recalculates the checksum for each file** that has already been applied to the database and will raise an error if it has been changed subsequently.


Flyway always puts the `flyway_schema_history` table in the default schema.   
On start-up, Flyway sees whether you have specified what schema should be the default schema, which you do using the `flyway.defaultSchema` setting.   
If you **haven’t** specified a default schema, Flyway uses the first schema in the list you provide in flyway.schemas.

If Flyway is asked to clean a database of all its contents, it removes the contents of each of the schemas, in the order of the `flyway.schemas` list and them creates and fills a fresh `flyway_schema_history` table.



## 3. Flyway Schema History Table
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

## 4. Bugs

### Issue 1:

org.springframework.beans.factory.BeanCreationException:
Error creating bean with name 'flywayInitializer' defined in class path resource 
[org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Invocation of init method failed; 
nested exception is java.lang.RuntimeException: 
Failed to get driver instance for `jdbcUrl=jdbc:postgresql://localhost:5432/spring-boot-postgres-db/demodb`


#### Issue 2:

Issue docker: Error response from daemon: Ports are not available: listen tcp 0.0.0.0:5432: bind: address already in use.
Soultion: `sudo lsof -i tcp:5432`

```
docker run  -d --name openjdk -it openjdk /bin/bash
docker exec -it $(docker ps | grep -E 'openjdk' | awk '{print $1}') /bin/bash
\l
```
API Layer / Controller Layer
Service Layer
Data Access Layer
```
lsof -i:5432 | awk 'NR==2 {print $2}'
java -jar demo-0.0.1-SNAPSHOT.jar
```
ps -A | grep psql | awk '{print $1}'
pgrep psql

```
docker stop  $(docker ps -a | grep -E 'postgres-spring' | awk '{print $1}' | awk 'NR==1') && docker rm  $(docker ps -a | grep -E 'postgres-spring' | awk '{print $1}' | awk 'NR==1')
docker run --name postgres-spring -v /Users/mac/singapore/person1:/root/person1 -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
docker exec -w /root/person1 -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash
psql -U postgres
docker port demodb
psql 
```


JDBC: Java Database Connectivity (JDBC)
flywaydb: applying new migrations

Well, they are just different data formats. Which one's nicer and easier to read? 
That's obviously subjective. Here's a useful blog post.

As far as spring-boot configuration is concerned, 
note that there's only one documented shortcoming of using YAML. Per the documentation:
YAML files can’t be loaded via the @PropertySource annotation. 
So in the case that you need to load values that way,
you need to use a properties file.


As per my knowledge, these are at least some of the differences:

1. `.properties` stores data in sequential format, whereas
.yml stores data in hierarchical format.
2 `.properties` supports only key-value pairs (basically string values), whereas
.yml supports key-value pair, as well as map, list & scalar type values.
3 `.properties` is specifically used by Java, whereas
.yml can be used by other languages (eg Java, Python, ROR, etc).
4 When managing multiple configuration profiles, then:
   `.properties` requires you to create .properties file per every profile, whereas in
`.yml` you can create a section for each specific profile inside a single `.yml` file.
2. In Spring projects, @PropertySource annotation can only be used with `.properties`.
