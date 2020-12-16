#!/bin/bash

echo "I will start once MySQL is ready."

while ! exec 6<>/dev/tcp/mysqlDb/3306; do
    echo "Trying to connect to MySQL..."
    sleep 40
done

echo "Got the MySQL connection"

exec "$@"