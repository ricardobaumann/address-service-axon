package com.github.ricardobaumann.addressserviceaxon.models

import com.github.ricardobaumann.addressserviceaxon.commands.AddressValidationCommand
import com.github.ricardobaumann.addressserviceaxon.commands.CreateAddressCommand
import com.github.ricardobaumann.addressserviceaxon.events.AddressCreatedEvent
import com.github.ricardobaumann.addressserviceaxon.events.AddressValidationEvent
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

internal class AddressAggregateTest {

    private lateinit var fixture: AggregateTestFixture<AddressAggregate>
    private lateinit var id: UUID

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(AddressAggregate::class.java)
        id = UUID.randomUUID()
    }


    @Test
    @DisplayName("When creating an address successfully it should publish an address created event")
    fun onAddressCreatedShouldPublishOrderCreatedEvent() {
        fixture.givenNoPriorActivity()
                .`when`(CreateAddressCommand(
                        id = id,
                        firstName = "test",
                        lastName = "last"
                )).expectEvents(AddressCreatedEvent(
                        id = id,
                        firstName = "test",
                        lastName = "last"
                ))
    }

    @Test
    fun onAddressValidationShouldPublishAddressValidatedEvent() {
        fixture.given(AddressCreatedEvent(
                id = id,
                firstName = "test",
                lastName = "last"
        )).`when`(AddressValidationCommand(
                id = id
        )).expectEvents(AddressValidationEvent(
                id = id,
                valid = true
        ))
    }

}