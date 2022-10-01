# truncate table
docker exec -w /root/person1/psql_command -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash /root/person1/psql_command/truncate_table.sh


# run table
docker exec -w /root/person1/psql_command -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash /root/person1/psql_command/run_table.sh
