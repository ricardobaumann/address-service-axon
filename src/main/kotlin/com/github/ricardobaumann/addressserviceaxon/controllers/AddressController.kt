package com.github.ricardobaumann.addressserviceaxon.controllers

import com.github.ricardobaumann.addressserviceaxon.commands.CreateAddressCommand
import com.github.ricardobaumann.addressserviceaxon.models.Address
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
class AddressController(private val commandGateway: CommandGateway) {

    @PostMapping("/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody createAddressCommand: CreateAddressCommand): CompletableFuture<Address> =
            commandGateway.send(createAddressCommand)

}