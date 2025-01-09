package com.pracitce.event_sorucing.domain.order

import com.pracitce.event_sorucing.domain.command.Command
import com.pracitce.event_sorucing.domain.order.service.OrderService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController (private val orderService: OrderService){

  @PostMapping
  fun createOrder(@RequestBody command: Command.CreateOrder){
    orderService.processCommand(command)
  }

  @PostMapping("/{orderId}/confirm")
  fun confirmOrder(@PathVariable orderId: String){
    val command = Command.ConfirmOrder(orderId)
    orderService.processCommand(command)
  }

  @PostMapping("/{orderId}/cancel")
  fun cancelOrder(@PathVariable orderId: String){
    val command = Command.CancelOrder(orderId)
    orderService.processCommand(command)
  }

}