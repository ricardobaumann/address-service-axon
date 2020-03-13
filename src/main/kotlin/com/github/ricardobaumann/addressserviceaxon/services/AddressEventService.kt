package com.github.ricardobaumann.addressserviceaxon.services

import com.github.ricardobaumann.addressserviceaxon.events.AddressCreatedEvent
import com.github.ricardobaumann.addressserviceaxon.events.AddressValidationEvent
import com.github.ricardobaumann.addressserviceaxon.models.Address
import com.github.ricardobaumann.addressserviceaxon.models.AddressStatus
import com.github.ricardobaumann.addressserviceaxon.repos.AddressRepo
import mu.KLogging
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AddressEventService(private val addressRepo: AddressRepo) {

    companion object : KLogging()

    @EventHandler
    fun on(addressCreatedEvent: AddressCreatedEvent) {
        addressRepo.save(Address(
                id = addressCreatedEvent.id,
                firstName = addressCreatedEvent.firstName,
                lastName = addressCreatedEvent.lastName,
                street = addressCreatedEvent.street,
                co = addressCreatedEvent.co,
                coType = addressCreatedEvent.coType,
                streetNumber = addressCreatedEvent.streetNumber,
                additionToAddress = addressCreatedEvent.additionToAddress,
                zip = addressCreatedEvent.zip,
                city = addressCreatedEvent.city,
                country = addressCreatedEvent.country,
                addressStatus = AddressStatus.CREATED
        ))
    }

    @Async
    @EventHandler
    fun on(addressValidationEvent: AddressValidationEvent) {
        logger.info("Saving address validation: {}", addressValidationEvent)
        addressValidationEvent.id?.let {
            addressRepo.findByIdOrNull(it)
        }?.let {
            addressRepo.save(it.apply {
                this.addressStatus =
                        if (addressValidationEvent.valid) AddressStatus.VALID
                        else AddressStatus.INVALID
            })
        }
    }

}