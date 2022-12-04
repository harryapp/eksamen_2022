resource "aws_cloudwatch_metric_alarm" "cart_alarm" {
  alarm_name                = "greater_than_5_carts_alarm"
  namespace                 = var.student_name
  metric_name               = "carts.value"

  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "3"
  period                    = "300"
  statistic                 = "Maximum"

  alarm_description         = "This alarm goes off as soon as total number of active carts exceeds 5 in 3 consecutive 5 minute intervals"
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.cart_count_limit_topic.arn]
}
