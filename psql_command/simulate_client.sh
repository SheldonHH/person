# 1. truncate tables
docker exec -w /root/person1/psql_command -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash /root/person1/psql_command/truncate_table.sh


# 2. golang training Node started
cd /Users/mac/singapore/person1/target
java -jar client1.jar >> client1.log &
java -jar client2.jar >> client2.log &
java -jar client3.jar >> client3.log &
java -jar client4.jar >> client4.log &

sleep 25
echo "sleep 25 second"

# 3. golang training Node data change trigger
cd /Users/mac/singapore/person1/src/main/golang
go run training_device_1.go >> go_client1.log &
go run training_device_2.go >> go_client2.log &
go run training_device_3.go >> go_client3.log &
go run training_device_4.go >> go_client4.log &