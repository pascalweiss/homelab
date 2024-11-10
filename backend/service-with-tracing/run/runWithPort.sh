#!/usr/bin/env bash

# read in the port number and service name, and throw an error when no params are given. Please use a common output for the error message.
if [ -z "$1" ] || [ -z "$2" ]; then
  echo "Use: gradle bootRun <port> <service_name>"
  exit 1
fi

SERVER_PORT="$1" SERVICE_NAME="$2" gradle bootRun