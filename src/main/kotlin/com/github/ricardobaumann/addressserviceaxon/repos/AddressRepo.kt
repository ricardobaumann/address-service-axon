package com.github.ricardobaumann.addressserviceaxon.repos

import com.github.ricardobaumann.addressserviceaxon.models.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepo : CrudRepository<Address, UUID>