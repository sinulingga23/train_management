#!/bin/bash
container_id=`docker ps | grep learn-spring-boot | awk '{print $1}'`
docker container stop $container_id