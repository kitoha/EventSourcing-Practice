package com.pracitce.event_sorucing.domain.aggregate

import com.pracitce.event_sorucing.domain.command.Command
import com.pracitce.event_sorucing.domain.event.OrderEvent

class OrderAggregate {

  private var orderId: String? = null
  private var product: String? = null
  private var quantity: Int = 0
  private var isConfirmed: Boolean = false
  private var isCancelled: Boolean = false

  fun apply(event: OrderEvent){
    when(event){
      is OrderEvent.OrderCreated -> {
        this.orderId = event.orderId
        this.product = event.product
        this.quantity = event.quantity
      }
      is OrderEvent.OrderConfirmed -> {
        this.isConfirmed = true
      }
      is OrderEvent.OrderCancelled ->{
        this.isCancelled = true
      }
    }
  }

  fun handle(command: Command): OrderEvent{
    return when(command){
      is Command.CreateOrder -> {
        if(command.quantity <= 0) throw IllegalArgumentException("Quantity must be greater than 0")

        OrderEvent.OrderCreated(command.orderId, command.product, command.quantity)
      }
      is Command.ConfirmOrder -> {
        if(isCancelled) throw IllegalStateException("Order is already cancelled and cannot be confirmed")

        OrderEvent.OrderConfirmed(command.orderId)
      }
      is Command.CancelOrder -> {
        OrderEvent.OrderCancelled(command.orderId)
      }
    }
  }

  override fun toString(): String {
    return "OrderAggregate(orderId=$orderId, product=$product, quantity=$quantity, isConfirmed=$isConfirmed, isCancelled=$isCancelled)"
  }
}