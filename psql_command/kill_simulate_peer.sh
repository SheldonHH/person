
#kill $(ps aux | grep peer1 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
#kill $(ps aux | grep peer2 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )




######################################################################################################################################################
#echo "killing peer and wait 2 seconds"
#sleep 2
#ps aux | grep peer1 | grep -v grep
#ps aux | grep peer2 | grep -v grep

# export pid=`ps aux | grep client2 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client3 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client4 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid

######################################################################################################################################################



ps aux | grep client1 | grep -v grep
ps aux | grep client2 | grep -v grep
ps aux | grep client3 | grep -v grep
ps aux | grep client4 | grep -v grep
ps aux | grep client5 | grep -v grep
ps aux | grep client6 | grep -v grep

kill $(ps aux | grep client1 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client2 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client3 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client4 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client5 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client6 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )

echo "killing client and wait 2 seconds"
sleep 2

ps aux | grep client1 | grep -v grep
ps aux | grep client2 | grep -v grep
ps aux | grep client3 | grep -v grep
ps aux | grep client4 | grep -v grep
ps aux | grep client5 | grep -v grep
ps aux | grep client6 | grep -v grep