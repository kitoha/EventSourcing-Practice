package com.pracitce.event_sorucing.domain.event

sealed class OrderEvent {
    data class OrderCreated(val orderId: String, val product: String, val quantity: Int) : OrderEvent() // 주문 생성
    data class OrderConfirmed(val orderId : String) : OrderEvent() // 주문 확인
    data class OrderCancelled(val orderId : String) : OrderEvent() // 주문 취소
}