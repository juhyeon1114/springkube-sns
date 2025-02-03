#!/bin/bash

helm -n sns install feed-server sns-chart.tgz -f values-dev.yaml