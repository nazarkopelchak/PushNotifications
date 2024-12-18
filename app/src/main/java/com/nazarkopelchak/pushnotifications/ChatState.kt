package com.nazarkopelchak.pushnotifications

data class ChatState(
    val isEnteringToken: Boolean = true,    // The app starts at the entering token screen
    val remoteToken: String = "",
    val messageText: String = ""
)
