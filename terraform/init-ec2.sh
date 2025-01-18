#!/bin/bash

mkdir -p /home/ubuntu/logs

# Install Docker
# https://docs.docker.com/engine/install/ubuntu/
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install -y ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo docker run hello-world > /home/ubuntu/logs/docker-install.log

# Give Docker perms to run without sudo
# https://docs.docker.com/engine/install/linux-postinstall/
sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker
docker run hello-world > /home/ubuntu/logs/docker-group-add.log

# Switch to ubuntu user root folder
cd /home/ubuntu

# Setup github actions runner
mkdir actions-runner && cd actions-runner
curl -o actions-runner-linux-x64-2.321.0.tar.gz -L https://github.com/actions/runner/releases/download/v2.321.0/actions-runner-linux-x64-2.321.0.tar.gz
echo "ba46ba7ce3a4d7236b16fbe44419fb453bc08f866b24f04d549ec89f1722a29e  actions-runner-linux-x64-2.321.0.tar.gz" | shasum -a 256 -c > /home/ubuntu/logs/github-actions-chechsum.log

tar xzf ./actions-runner-linux-x64-2.321.0.tar.gz

# Switch to ubuntu user for this script as github actions will not allow the script to be run as root user
sudo -u ubuntu bash <<EOF
./config.sh --url https://github.com/Ghersi75/GameThreads --token <token> --labels test --unattended > /home/ubuntu/logs/github-actions-config.log
EOF

# Go back to ubuntu user root folder
cd ../

# Set up github actions service
cat <<EOF > /etc/systemd/system/github-actions-runner.service
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

sudo systemctl enable github-actions-runner
sudo systemctl start github-actions-runner