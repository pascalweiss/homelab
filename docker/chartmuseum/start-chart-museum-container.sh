#!/usr/bin/env bash

# Default values
USER_NAME="chartuser"
USER_PASS="mypass"
INTERACTIVE_FLAG="-it"
VOLUME_NAME="chartmuseum-charts"

# Function to display usage instructions
usage() {
    echo "Usage: $0 [-d] [--user USER_NAME] [--password USER_PASS] [--volume VOLUME_NAME]"
    echo "  -d                     Run the container in the background"
    echo "  --user USER_NAME       Specify the username (default: chartuser)"
    echo "  --password USER_PASS   Specify the password (default: mypass)"
    echo "  --volume VOLUME_NAME   Specify the Docker volume name (default: chartmuseum-charts)"
    exit 1
}

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        -d) INTERACTIVE_FLAG="-d" ;;
        --user) USER_NAME="$2"; shift ;;
        --password) USER_PASS="$2"; shift ;;
        --volume) VOLUME_NAME="$2"; shift ;;
        *) echo "Unknown parameter passed: $1"; usage ;;
    esac
    shift
done

# Create Docker volume if it doesn't exist
if ! sudo docker volume inspect "$VOLUME_NAME" &>/dev/null; then
    echo "Creating Docker volume: $VOLUME_NAME"
    sudo docker volume create "$VOLUME_NAME"
    sudo docker run --rm -it \
      -v "$VOLUME_NAME":/charts \
      alpine sh -c "chown 1000:1000 /charts && chmod 775 /charts"
fi

echo ""
echo "Charts are stored in Docker volume: $VOLUME_NAME"
echo ""

sudo docker run --rm $INTERACTIVE_FLAG \
  -p 8080:8080 \
  -e DEBUG=1 \
  -e STORAGE=local \
  -e STORAGE_LOCAL_ROOTDIR=/charts \
  -e BASIC_AUTH_USER="${USER_NAME}" \
  -e BASIC_AUTH_PASS="${USER_PASS}" \
  -v "${VOLUME_NAME}:/charts" \
  ghcr.io/helm/chartmuseum:v0.16.2
