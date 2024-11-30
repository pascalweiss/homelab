#!/usr/bin/env bash

# Define the target folder
PACKAGE_FOLDER="./package"

# Create the package folder if it doesn't exist
mkdir -p "${PACKAGE_FOLDER}"

# Package the Helm chart and specify the package folder
helm package . --destination "${PACKAGE_FOLDER}"