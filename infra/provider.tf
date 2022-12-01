terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }

  backend "s3" {
    bucket = "analytics-1037"
    key    = "terraform.state"
    region = "eu-north-1"
  }

}

