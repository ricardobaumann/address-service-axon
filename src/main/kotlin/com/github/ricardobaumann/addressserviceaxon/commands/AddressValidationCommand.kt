package com.github.ricardobaumann.addressserviceaxon.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddressValidationCommand(
        @get:TargetAggregateIdentifier val id: UUID? = null,
        val street: String? = null,
        val co: String? = null,
        val coType: String? = null,
        val streetNumber: String? = null,
        val additionToAddress: String? = null,
        val zip: String? = null,
        val city: String? = null,
        val country: String? = null
)