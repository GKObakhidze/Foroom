package com.example.network.training

import com.example.network.web_socket.ForoomWebSocketClient
import com.example.shared.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

/** Local chat viewing without a remote SignalR connection. */
class TrainingChatClient : ForoomWebSocketClient {
    override fun connect(): Flow<Result<Unit>> = flowOf(Result.Success(Unit))
    override fun disconnect() = Unit
    override fun <T> onReceived(dataClass: Class<T>): Flow<T> = emptyFlow()
    override fun joinGroup(groupName: String): Flow<Result<Unit>> = flowOf(Result.Success(Unit))
    override fun leaveGroup(groupName: String): Flow<Result<Unit>> = flowOf(Result.Success(Unit))
    override fun sendMessage(data: Any): Flow<Result<Unit>> =
        flowOf(Result.Error(UnsupportedOperationException("Messaging is unavailable in training mode")))
}
