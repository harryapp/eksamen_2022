terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }

  backend "s3" {
    bucket = "terraform-state-1037"
    key    = "shopifly/terraform.state"
    region = "eu-north-1"
  }

}

