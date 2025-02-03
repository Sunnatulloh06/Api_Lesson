package com.example.practicecompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppScreen() {
    val email = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val responseMessage = remember { mutableStateOf("Here will appear Response from Server") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = code.value,
            onValueChange = { code.value = it.filter { it.isDigit() } },
            label = { Text("Code") }
        )

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = sendEmail(email = email.value, code = code.value.toInt())
                    responseMessage.value = response.token ?: "Error in request"
                }
            }
        ) {
            Text("Send")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            responseMessage.value
        )
    }
}