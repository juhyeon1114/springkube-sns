#!/bin/bash

kubectl create namespace monitoring
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm -n monitoring install prometheus prometheus-community/prometheus
helm repo add grafana https://grafana.github.io/helm-charts
helm -n monitoring install grafana grafana/grafana --set adminPassword="admin01" --values ./grafana.yaml

kubectl port-forward -n monitoring deployment/grafana 3000