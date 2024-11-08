#!/usr/bin/env bash

# read in the port number and throw an error, when no param is given. Please use a common output for the error message.
if [ -z "$1" ]; then
  echo "Use: gradle bootRun <port>"
  exit 1
fi


SERVER_PORT="$1" gradle bootRun
