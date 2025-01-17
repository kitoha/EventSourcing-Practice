package com.pracitce.event_sorucing.domain.query

sealed class Query {
  data class GetOrderById(val orderId: String) : Query()
  object GetAllOrders : Query()
}