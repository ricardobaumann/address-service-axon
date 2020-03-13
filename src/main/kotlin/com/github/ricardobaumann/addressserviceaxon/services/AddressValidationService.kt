package com.github.ricardobaumann.addressserviceaxon.services

import com.github.ricardobaumann.addressserviceaxon.events.AddressCreatedEvent
import com.github.ricardobaumann.addressserviceaxon.events.AddressValidationEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AddressValidationService(private val eventGateway: EventGateway) {

    @Async
    @EventHandler
    fun on(addressCreatedEvent: AddressCreatedEvent) {
        Thread.sleep(5000)//heavy process
        eventGateway.publish(AddressValidationEvent(
                id = addressCreatedEvent.id,
                valid = true
        ))
    }

}