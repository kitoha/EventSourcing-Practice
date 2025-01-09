package com.pracitce.event_sorucing.domain.order.service

import com.pracitce.event_sorucing.domain.aggregate.OrderAggregate
import com.pracitce.event_sorucing.domain.command.Command
import com.pracitce.event_sorucing.domain.event.service.EventStoreService
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val eventStoreService: EventStoreService
){
  fun processCommand(command: Command){
    val events = eventStoreService.getEvents(command.orderId)
    val aggregate = OrderAggregate()
    events.forEach{aggregate.apply(it)}

    val newEvent = aggregate.handle(command)

    eventStoreService.save(newEvent, command.orderId)

    aggregate.apply(newEvent)

  }

}