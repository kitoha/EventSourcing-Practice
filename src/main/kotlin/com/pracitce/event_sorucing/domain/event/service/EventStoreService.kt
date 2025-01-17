package com.pracitce.event_sorucing.domain.event.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.pracitce.event_sorucing.domain.event.OrderEvent
import com.pracitce.event_sorucing.domain.event.entity.StoredEvent
import com.pracitce.event_sorucing.domain.event.repoistory.EventStoreRepository
import org.springframework.stereotype.Service

@Service
class EventStoreService (
    private val repository : EventStoreRepository,
    private val objectMapper : ObjectMapper
){
  fun save(orderEvent: OrderEvent, orderId: String){
    val storedEvent = StoredEvent(
        orderId = orderId,
        eventType = orderEvent::class.simpleName?: "UnknownEvent",
        eventPayload = objectMapper.writeValueAsString(orderEvent)
    )
    repository.save(storedEvent)
  }

  fun getEvents(orderId: String) : List<OrderEvent>{
    return repository.findByOrderId(orderId).map {
      objectMapper.readValue(it.eventPayload, OrderEvent::class.java)
    }
  }

  fun findAllEvents(): List<OrderEvent>{
    return repository.findAll().map { storedEvent -> objectMapper.readValue(storedEvent.eventPayload, OrderEvent::class.java) }
  }
}