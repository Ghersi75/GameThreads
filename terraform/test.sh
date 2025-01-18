#!/bin/bash

# Ensure the .env file is sourced from the script's directory
SCRIPT_DIR="$(dirname "$0")"
source "$SCRIPT_DIR/.env"

# Check if the variable is set
if [ -z "$TEST" ]; then
  echo "TEST variable is not set"
  exit 1
else
  echo "TEST variable is set: $TEST"
fi


cat <<EOF > service.txt
[Unit]
Description=GitHub Actions Runner
After=network-online.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/actions-runner
ExecStart=/home/ubuntu/actions-runner/run.sh
Restart=always
RestartSec=5s

[Install]
WantedBy=multi-user.target
EOF
