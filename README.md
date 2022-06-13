# demo_for_spring
### 

Gaussian Mixture Model
A Gaussian mixture model is a probabilistic model that assumes all the data points are generated from a mixture of a finite number of Gaussian distributions with unknown parameters. 
One can think of mixture models as generalizing k-means clustering to incorporate information about the covariance structure of the data as well as the centers of the latent Gaussians.
The GaussianMixture object implements the expectation-maximization (EM) algorithm for fitting mixture-of-Gaussian models. 
It can also draw confidence ellipsoids for multivariate models, and compute the Bayesian Information Criterion to assess the number of clusters in the data. 

A GaussianMixture.fit method is provided that learns a Gaussian Mixture Model from train data. Given test data, it can assign to each sample the Gaussian it mostly probably belong to using the GaussianMixture.predict method.
P4P (Peers for Privacy) and features a unique architecture and practical protocols for user data validation and vector addition-based computation.
It turned out that many non-trivial and non-linear computations can be done using an iterative algorithm with vector-addition aggregation steps.



### Call from Golang

### Q1

org.springframework.beans.factory.BeanCreationException:

Error creating bean with name 'flywayInitializer' defined in class path resource 
[org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]
: Invocation of init method failed; 
nested exception is java.lang.RuntimeException: 
Failed to get driver instance for 
jdbcUrl=jdbc:postgresql://localhost:5432/spring-boot-postgres-db/demodb

### S

### Question: docker: Error response from daemon: Ports are not available: listen tcp 0.0.0.0:5432: bind: address already in use.

### Soultion: `sudo lsof -i tcp:5432`

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

```
psql -U postgres
create database postgres-spring;
create database spring-boot-postgres-db;
\l

\c registration
\dt
SELECT * FROM app_user;
drop table app_user, confirmation_token;

UPDATE app_user SET enabled=TRUE WHERE EMAIL='sg@gmail.com';
UPDATE app_user SET enabled=TRUE WHERE EMAIL='hellow@gmail.com';

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
# person
# codejam
