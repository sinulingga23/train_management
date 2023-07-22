#!/bin/bash
container_id=`docker ps -a | grep learn-spring-boot | awk '{print $1}'`
docker container start $container_id