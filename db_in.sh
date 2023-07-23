#!/bin/bash
container_id=`docker ps | grep learn-spring-boot | awk '{print $1}'`
docker exec -it $container_id /bin/sh
