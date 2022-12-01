# Jim; this just fails ... commented it out ! We need to figure this out later, starting new task instead...

resource "aws_s3_bucket" "analyticsbucket" {
  bucket = "analytics-${var.candidate_id}"
}

resource "aws_s3_bucket" "terraform-state" {
  bucket = "terraform-state-${var.candidate_id}"
}
