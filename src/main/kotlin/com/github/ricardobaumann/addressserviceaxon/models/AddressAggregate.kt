package com.github.ricardobaumann.addressserviceaxon.models

import com.github.ricardobaumann.addressserviceaxon.commands.AddressValidationCommand
import com.github.ricardobaumann.addressserviceaxon.commands.CreateAddressCommand
import com.github.ricardobaumann.addressserviceaxon.events.AddressCreatedEvent
import com.github.ricardobaumann.addressserviceaxon.events.AddressValidationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class AddressAggregate @CommandHandler constructor(createAddressCommand: CreateAddressCommand?) {
    @AggregateIdentifier
    private var id: UUID? = null

    private var firstName: String? = null
    private var lastName: String? = null
    private var street: String? = null
    private var co: String? = null
    private var coType: String? = null
    private var streetNumber: String? = null
    private var additionToAddress: String? = null
    private var zip: String? = null
    private var city: String? = null
    private var country: String? = null
    private var addressStatus: AddressStatus = AddressStatus.CREATED
    private var validationReason: String? = null

    protected constructor() : this(null)

    init {
        createAddressCommand?.also {
            AggregateLifecycle.apply(AddressCreatedEvent(
                    id = createAddressCommand.id,
                    firstName = createAddressCommand.firstName,
                    lastName = createAddressCommand.lastName,
                    street = createAddressCommand.street,
                    co = createAddressCommand.co,
                    coType = createAddressCommand.coType,
                    streetNumber = createAddressCommand.streetNumber,
                    additionToAddress = createAddressCommand.additionToAddress,
                    zip = createAddressCommand.zip,
                    city = createAddressCommand.city,
                    country = createAddressCommand.country
            ))
        }

    }

    @EventSourcingHandler
    fun on(addressCreatedEvent: AddressCreatedEvent) {
        id = addressCreatedEvent.id
        firstName = addressCreatedEvent.firstName
        lastName = addressCreatedEvent.lastName
        street = addressCreatedEvent.street
        co = addressCreatedEvent.co
        coType = addressCreatedEvent.coType
        streetNumber = addressCreatedEvent.streetNumber
        additionToAddress = addressCreatedEvent.additionToAddress
        zip = addressCreatedEvent.zip
        city = addressCreatedEvent.city
        country = addressCreatedEvent.country
        addressStatus = AddressStatus.CREATED
    }

    @CommandHandler
    fun handle(addressValidationCommand: AddressValidationCommand) {
        AggregateLifecycle.apply(AddressValidationEvent(
                id = addressValidationCommand.id,
                valid = true
        ))
    }

    @EventSourcingHandler
    fun on(addressValidationEvent: AddressValidationEvent) {
        validationReason = addressValidationEvent.reason
        addressStatus = if (addressValidationEvent.valid) AddressStatus.VALID else AddressStatus.INVALID
    }
}