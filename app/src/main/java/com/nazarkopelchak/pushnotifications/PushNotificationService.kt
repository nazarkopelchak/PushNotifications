package com.nazarkopelchak.pushnotifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Generates a new token. Should be stored on the server.
    }


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Resound to received messages
    }
}