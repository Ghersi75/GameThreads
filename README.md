# GameThreads
Name is not final. This is going to be a project to put everything I've worked on so far into 1 big project as well as learning more cloud related things with Terraform and Ansible for provisioning and configuring cloud resources, primary EC2 instances.

## Table of Contents
- [Frontend](#frontend)
- [Backend](#backend)
- [CI/CD](#cicd)
- [Terraform](#terraform)
- [Ansible](#ansible)

## Frontend
Originally I was going to go with React, but NextJS makes routing easier to handle and has server side rendering (SSR) out of the box. Definitely a nice to have.

## Backend
Since I've been working with Spring Boot for a couple months now, I'll be using it for this project as well. I've also been using postgres for the past couple months so I'll be using it for this as well. I plan on implementing some caching, most likely for image links with signed s3 links, so I'll most likely have a redis cache as well.

## CI/CD
I've worked with both Jenkins and Github Actions, and I might stick to using Github Actions due to how easy it is to work with. I might use Jenkins, but setting up and EC2 instance that could handle Jenkins might cost something like $20-30 a month while Github Actions could handle everything for free, so I'll most likely stick to using Github Actions.

## Terraform
I'll be using Terraform to spin the cloud infrastructure and destroy it when I don't need it. This is mostly to get familiar with it, but also to with not having to manually spin things up and tear them down on aws.

## Ansible
When EC2 instances are spun up they need setup. I tried messing around with user_data script with Terraform but it simply wasn't easy to work with and only allowed a single script to run when the instances started up. This is where Ansible comes in. It's typically used for linux automation, which is perfect since I'll be using Ubuntu 24.04 on the EC2 instances. Ansible can essentially run any number of scripts on groups of EC2 instances. It is currently able to install docker, setup github actions runners, and remove those runners. This does require some manual running of scripts, but it is significantly easier and better than having to manually ssh into all the machines.