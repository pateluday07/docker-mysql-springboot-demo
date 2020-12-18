#!/bin/bash

while ! exec 6<>/dev/tcp/${DOCKER_MYSQL_SPRINGBOOT_DEMO_MYSQL_HOST}/3306; do
    echo "Trying to connect to MySQL..."
    sleep 40
done

echo "Got the MySQL connection"

exec "$@"