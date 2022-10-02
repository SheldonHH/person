
ps aux | grep peer1
ps aux | grep peer2

kill $(ps aux | grep peer1 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )
kill $(ps aux | grep peer2 | grep -v grep | awk 'NR==1{print $2}' | cut -d' ' -f1 )

echo "sleep 30 seconds"
sleep 30



ps aux | grep peer1
ps aux | grep peer2
# export pid=`ps aux | grep client2 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client3 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid
# export pid=`ps aux | grep client4 | awk 'NR==1{print $2}' | cut -d' ' -f1`;kill -9 $pid