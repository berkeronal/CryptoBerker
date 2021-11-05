package com.berker.cryptoberker.data.remote.dto

import com.berker.cryptoberker.domain.model.CoinEvent
import com.google.gson.annotations.SerializedName

data class CoinEventDto(
    val date: String,
    @SerializedName("date_to")
    val dateTo: String,
    val description: String,
    val id: String,
    @SerializedName("is_conference")
    val isConference: Boolean,
    val link: String,
    val name: String,
    @SerializedName("proof_image_link")
    val proofImageLink: String,
)

fun CoinEventDto.toCoinEvent(): CoinEvent {
    return CoinEvent(
        date = date,
        dateTo = dateTo,
        description = description,
        id = id,
        link = link,
        name = name,
        proofImageLink = proofImageLink
    )
}