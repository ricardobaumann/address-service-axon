package com.github.ricardobaumann.addressserviceaxon.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "addresses")
data class Address(
        @Id val id: UUID? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val street: String? = null,
        val co: String? = null,
        val coType: String? = null,
        val streetNumber: String? = null,
        val additionToAddress: String? = null,
        val zip: String? = null,
        val city: String? = null,
        val country: String? = null,
        var addressStatus: AddressStatus = AddressStatus.CREATED
)