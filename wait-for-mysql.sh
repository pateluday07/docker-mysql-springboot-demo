#!/bin/bash

while ! exec 6<>/dev/tcp/docker-mysql-springboot-demo-db/3306; do
    echo "Trying to connect to MySQL..."
    sleep 40
done

echo "Got the MySQL connection"

exec "$@"