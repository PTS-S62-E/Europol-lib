package com.pts.europollib

import com.google.gson.Gson
import okhttp3.*

class EuropolLib(
        private val config: EuropolConfig = EuropolConfig()
) {
    private val httpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val applicationJson = MediaType.parse("application/json")

    fun getVehicles(): List<EuropolVehicle> {
        val request = Request.Builder().url(this.config.createUrl()).get().build()
        val response = this.httpClient.newCall(request).execute()
        val responseBody = response.body()!!.string()
        return Gson().fromJson(responseBody)
    }

    fun getVehicleBySerial(serialNumber: String): EuropolVehicle {
        val request = Request.Builder()
                .url("${this.config.createUrl()}$serialNumber")
                .get()
                .build()
        val response = this.httpClient.newCall(request).execute()
        val responseBody = response.body()!!.string()
        return Gson().fromJson(responseBody)
    }

    fun insertVehicle(europolVehicle: EuropolVehicle): EuropolVehicle {
        val request = Request.Builder()
                .url(this.config.createUrl())
                .post(RequestBody.create(this.applicationJson, europolVehicle.toJson()))
                .header("Content-Type", "application/json")
                .build()
        val response = this.httpClient.newCall(request).execute()
        return Gson().fromJson(response.body()!!.string())
    }

    fun deleteVehicle(serialNumber: String): Boolean {
        val request = Request.Builder()
                .url("${this.config.createUrl()}$serialNumber/")
                .header("Content-Length", "0")
                .delete(RequestBody.create(MediaType.parse("text/plain"), ""))
                .build()
        val response = this.httpClient.newCall(request).execute()
        return response.isSuccessful
    }
}