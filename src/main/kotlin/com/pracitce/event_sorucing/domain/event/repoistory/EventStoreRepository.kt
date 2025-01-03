package com.pracitce.event_sorucing.domain.event.repoistory

import com.pracitce.event_sorucing.domain.event.entity.StoredEvent
import org.springframework.data.jpa.repository.JpaRepository

interface EventStoreRepository : JpaRepository<StoredEvent, Long>{
    fun findByOrderId(orderId: String): List<StoredEvent>
}