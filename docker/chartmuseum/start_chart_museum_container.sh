#! /usr/bin/env bash

# Default values
USER_NAME="chartuser"
USER_PASS="mypass"

# Function to display usage instructions
usage() {
    echo "Usage: $0 [--user USER_NAME] [--password USER_PASS]"
    echo "  --user USER_NAME       Specify the username (default: chartuser)"
    echo "  --password USER_PASS   Specify the password (default: mypass)"
    exit 1
}

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --user) USER_NAME="$2"; shift ;;
        --password) USER_PASS="$2"; shift ;;
        *) echo "Unknown parameter passed: $1"; usage ;;
    esac
    shift
done

echo ""
echo "Charts are stored in $(pwd)/charts"
echo ""

sudo docker run --rm -it \
  -p 8080:8080 \
  -e DEBUG=1 \
  -e STORAGE=local \
  -e STORAGE_LOCAL_ROOTDIR=/charts \
  -e BASIC_AUTH_USER="${USER_NAME}" \
  -e BASIC_AUTH_PASS="${USER_PASS}" \
  -v "$(pwd)"/charts:/charts \
  ghcr.io/helm/chartmuseum:v0.16.2