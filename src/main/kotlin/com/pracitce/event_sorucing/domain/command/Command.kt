package com.pracitce.event_sorucing.domain.command

sealed class Command (
    open val orderId: String
){
  data class CreateOrder(override val orderId: String, val product: String, val quantity: Int): Command(orderId)
  data class ConfirmOrder(override val orderId: String) : Command(orderId)
  data class CancelOrder(override val orderId: String) : Command(orderId)
}