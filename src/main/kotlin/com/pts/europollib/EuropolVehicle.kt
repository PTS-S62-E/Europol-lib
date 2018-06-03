package com.pts.europollib

import java.io.Serializable

data class EuropolVehicle(
        val HashedLicensePlate: String = "",
        val serialNumber: String = "",
        val originCountry: String = ""
) : Serializable