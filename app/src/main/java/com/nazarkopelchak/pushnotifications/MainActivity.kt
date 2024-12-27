package com.nazarkopelchak.pushnotifications

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nazarkopelchak.pushnotifications.ui.theme.PushNotificationsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermissions()
        enableEdgeToEdge()
        setContent {
            PushNotificationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val state = viewModel.state
                        if (state.isEnteringToken) {
                            EnterTokenDialog(
                                token = state.remoteToken,
                                onTokenChange = viewModel::onRemoteTokenChange,
                                onSubmitToken = viewModel::onSubmitRemoteToken
                            )
                        }
                        else {
                            ChatScreen(
                                messageText = state.messageText,
                                onMessageChange = viewModel::onMessageChange,
                                onMessageSend = {
                                    viewModel.sendMessage(isBroadcast = false)
                                },
                                onMessageBroadcast = {
                                    viewModel.sendMessage(isBroadcast = true)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun requestNotificationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
}