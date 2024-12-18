package com.nazarkopelchak.pushnotifications

data class SendMessageDto(
    val token: String?, // To whom send this message
    val notification: NotificationBody
)

data class NotificationBody(
    val title: String,
    val body: String
)
