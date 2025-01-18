resource "aws_instance" "test" {
  # Ubuntu Server 24.04 LTS - us-east-2
  ami                    = "ami-0cb91c7de36eed2cb"
  instance_type          = "t2.micro"
  count                  = 5
  vpc_security_group_ids = [aws_security_group.TF_SG.id]
  # user_data              = file("init-ec2.sh")
  # subnet_id                   = aws_subnet.default.id
  associate_public_ip_address = "true"
  key_name                    = "TF_Key"

  tags = {
    Name = "HelloWorld"
  }
}

resource "aws_key_pair" "TF_Key" {
  key_name   = "TF_Key"
  public_key = tls_private_key.rsa.public_key_openssh
}

resource "tls_private_key" "rsa" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "local_file" "TF_Key" {
  content  = tls_private_key.rsa.private_key_pem
  filename = "TF_Key.pem"
}

resource "aws_default_vpc" "default" {
  tags = {
    Name = "Default VPC"
  }
}

resource "aws_security_group" "TF_SG" {
  name        = "TF_SG"
  description = "Security group using terraform allowing TLS inbound traffic and all outbound traffic"
  vpc_id      = aws_default_vpc.default.id

  tags = {
    Name = "TF_SG"
  }
}

resource "aws_vpc_security_group_ingress_rule" "allow_tls_ipv4" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 443
  ip_protocol       = "tcp"
  to_port           = 443
}

resource "aws_vpc_security_group_ingress_rule" "allow_tls_ipv6" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv6         = "::/0"
  from_port         = 443
  ip_protocol       = "tcp"
  to_port           = 443
}

resource "aws_vpc_security_group_ingress_rule" "allow_http_ipv4" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 80
  ip_protocol       = "tcp"
  to_port           = 80
}

resource "aws_vpc_security_group_ingress_rule" "allow_http_ipv6" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv6         = "::/0"
  from_port         = 80
  ip_protocol       = "tcp"
  to_port           = 80
}

resource "aws_vpc_security_group_ingress_rule" "allow_ssh_ipv4" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 22
  ip_protocol       = "tcp"
  to_port           = 22
}

resource "aws_vpc_security_group_ingress_rule" "allow_ssh_ipv6" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv6         = "::/0"
  from_port         = 22
  ip_protocol       = "tcp"
  to_port           = 22
}

resource "aws_vpc_security_group_egress_rule" "allow_all_outgoing_ipv4" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 0
  to_port           = 65535
  ip_protocol       = "tcp"
}

resource "aws_vpc_security_group_egress_rule" "allow_all_outgoing_ipv6" {
  security_group_id = aws_security_group.TF_SG.id
  cidr_ipv6         = "::/0"
  from_port         = 0
  to_port           = 65535
  ip_protocol       = "tcp"
}

output "test_instance_public_ip" {
  description = "Public IP of the test EC2 instance"
  value       = aws_instance.test.*.public_ip
}