
ps aux | grep client1
ps aux | grep client2
ps aux | grep client3
ps aux | grep client4

kill $(ps aux | grep client1 | awk 'NR==2{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client2 | awk 'NR==2{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client3 | awk 'NR==2{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep client4 | awk 'NR==2{print $2}' | cut -d' ' -f1 )

echo "sleep 30 seconds"
sleep 30



ps aux | grep client1
ps aux | grep client2
ps aux | grep client3
ps aux | grep client4
# export pid=`ps aux | grep client2 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client3 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client4 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid