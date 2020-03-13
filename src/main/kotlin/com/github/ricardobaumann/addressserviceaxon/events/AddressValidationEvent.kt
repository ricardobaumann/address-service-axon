package com.github.ricardobaumann.addressserviceaxon.events

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddressValidationEvent(
        @get:TargetAggregateIdentifier val id: UUID? = null,
        val valid: Boolean = false,
        val reason: String? = null
)