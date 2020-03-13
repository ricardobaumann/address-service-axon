package com.github.ricardobaumann.addressserviceaxon.events

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddressCreatedEvent(
        @get:TargetAggregateIdentifier val id: UUID? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val street: String? = null,
        val co: String? = null,
        val coType: String? = null,
        val streetNumber: String? = null,
        val additionToAddress: String? = null,
        val zip: String? = null,
        val city: String? = null,
        val country: String? = null
)