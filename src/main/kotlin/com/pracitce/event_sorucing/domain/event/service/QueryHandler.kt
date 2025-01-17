package com.pracitce.event_sorucing.domain.event.service

import com.pracitce.event_sorucing.domain.aggregate.OrderAggregate
import com.pracitce.event_sorucing.domain.event.OrderEvent
import com.pracitce.event_sorucing.domain.query.Query
import org.springframework.stereotype.Service

@Service
class QueryHandler(
    private val eventStoreService: EventStoreService
) {

  fun handle(query: Query.GetOrderById): OrderAggregate?{
    val events = eventStoreService.getEvents(query.orderId)
    if(events.isEmpty()) return null

    val aggregate = OrderAggregate()
    events.forEach { aggregate.apply(it) }
    return aggregate
  }

  fun handle(query: Query.GetAllOrders): List<OrderAggregate>{
    val orders = mutableMapOf<String, OrderAggregate>()

    eventStoreService.findAllEvents().forEach { event ->
      val orderId = when(event){
        is OrderEvent.OrderCreated -> event.orderId
        is OrderEvent.OrderConfirmed -> event.orderId
        is OrderEvent.OrderCancelled -> event.orderId
      }
      orders.computeIfAbsent(orderId){
        OrderAggregate()
      }.apply(event)
    }

    return orders.values.toList()
  }
}