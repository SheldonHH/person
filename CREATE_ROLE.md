```
docker exec -it $(docker ps | grep -E 'postgres-spring' | awk '{print $1}') /bin/bash
psql -U postgres
```