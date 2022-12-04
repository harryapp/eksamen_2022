resource "aws_sns_topic" "cart_count_limit_topic" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "cart_count_limit_topic_sub" {
  topic_arn = aws_sns_topic.cart_count_limit_topic.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}