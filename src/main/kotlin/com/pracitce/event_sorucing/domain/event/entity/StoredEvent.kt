package com.pracitce.event_sorucing.domain.event.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob
import java.time.Instant

@Entity
data class StoredEvent (
    @Id
    @GeneratedValue val id : Long? = null,
    val orderId: String,
    val eventType: String,
    @Lob val eventPayload : String,
    val occurredAt : Instant = Instant.now()
)