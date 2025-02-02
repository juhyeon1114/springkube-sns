#!/bin/bash

kubectl create namespace infra
helm -n infra install mysql oci://registry-1.docker.io/bitnamicharts/mysql --set primary.persistence.enabled=false --set auth.rootPassword=password!
helm -n infra install redis oci://registry-1.docker.io/bitnamicharts/redis --set architecture=standalone --set auth.enabled=false --set master.persistence.enabled=false
helm -n infra install kafka oci://registry-1.docker.io/bitnamicharts/kafka --set controller.replicaCount=3  --set sasl.client.passwords=kafkakafka123! --set controller.persistence.enabled=false --set broker.persistence.enabled=false