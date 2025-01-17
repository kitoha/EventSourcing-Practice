package com.pracitce.event_sorucing.domain.query

import com.pracitce.event_sorucing.domain.aggregate.OrderAggregate
import com.pracitce.event_sorucing.domain.event.service.QueryHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class QueryController (
    private val queryHandler: QueryHandler
){

  @GetMapping("/{orderId}")
  fun getOrderById(@PathVariable orderId: String): ResponseEntity<OrderAggregate>{
    val order = queryHandler.handle(Query.GetOrderById(orderId))
    return if(order != null) ResponseEntity.ok(order) else ResponseEntity.notFound().build()
  }

  @GetMapping
  fun getAllOrders(): List<OrderAggregate>{
    return queryHandler.handle(Query.GetAllOrders)
  }
}