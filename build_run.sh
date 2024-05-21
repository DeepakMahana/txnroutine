#!/bin/bash

# Build Docker image
docker build -t txnroutine-svc .

# Run Docker container
docker run -d --name txnroutine-svc-container -p 8080:8080 txnroutine-svc
