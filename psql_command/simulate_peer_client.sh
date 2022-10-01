# 1. truncate tables
docker exec -w /root/person1/psql_command -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash /root/person1/psql_command/truncate_table.sh

# 2.1 peer1 triggered by golang
cd /Users/mac/singapore/peer1/target
java -jar peer1.jar >> peer1.log  &

# 2.2 training set 1 triggered by golang
cd /Users/mac/singapore/person1/target
java -jar client1.jar >> client1.log &
java -jar client2.jar >> client2.log &
# java -jar client3.jar >> client3.log &
# java -jar client4.jar >> client4.log &

echo "sleep 25 second"
sleep 25

# 2.3. golang data change send data
cd /Users/mac/singapore/person1/src/main/golang
go run training_device_1.go >> go_client1.log &
go run training_device_2.go >> go_client2.log &
# go run training_device_3.go >> go_client3.log &
# go run training_device_4.go >> go_client4.log &

#####
# Peer 2
####

# 3.1 peer2 triggered by golang
cd /Users/mac/singapore/peer2/target
java -jar peer2.jar >> peer2.log  &

# 3.2 training set 2 triggered by golang
java -jar client5.jar >> client5.log &
java -jar client6.jar >> client6.log &

# 3.3 golang data change send data
go run training_device_5.go >> go_client5.log &
go run training_device_6.go >> go_client6.log &