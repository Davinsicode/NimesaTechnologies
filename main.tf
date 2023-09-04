// Creating AWS Provider
provider "aws" {
  region = "us-east-1"
  access_key = "AKIAWCXV3J3WALTTMQH3"
  secret_key = "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"
}

// Creating Resource AWS Instance
resource "aws_instance" "my-first-server" {
  ami           = "ami-051f7e7f6c2f40dc1"
  instance_type = "t2.micro"

// Creating the Volume
root_block_device {
    volume_size = 8
    volume_type = "gp2"
    delete_on_termination = true
  }

// Creating Tags
  tags = {
    Purpose = "Assignment"
  }
}

// Creating Resource AWS VPC (Virtual Private Cloud)
resource "aws_vpc" "my_first_vpc" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Name = "My First VPC"
  }
} 

// Creating Resource AWS Subnet
resource "aws_subnet" "Public_Subnet" {
  vpc_id     = aws_vpc.my_first_vpc.id
  cidr_block = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  // For Public
  map_public_ip_on_launch = true
  tags = {
    Name = "Public Subnet"
  }
}
resource "aws_subnet" "Private_Subnet" {
  vpc_id     = aws_vpc.my_first_vpc.id
  cidr_block = "10.0.2.0/24"
  availability_zone       = "us-east-1a"
  tags = {
    Name = "Private Subnet"
  }
}

// Creating AWS Security Group
resource "aws_security_group" "My_Security_Group" {
  name        = "Security_Group"
  description = "Allow SSH inbound and All outbound traffic"
  vpc_id      = aws_vpc.my_first_vpc.id

  ingress {
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    // Any Ip Address can access --> 0.0.0.0/0 it is a default
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }
}