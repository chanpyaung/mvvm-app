package com.chanaung.mvvmapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class NoConnectionInterceptor(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isConnected().not() or isReachedToInternet().not()) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val connection = connectivityManager.getNetworkCapabilities(network)
        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                )
    }

    private fun isReachedToInternet(): Boolean {
        return try {
            val timeOutMs = 1000
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, timeOutMs)
            sock.close()
            true
        } catch(e: IOException) {
            false
        }
    }

    class NoConnectivityException: IOException() {
        override val message: String
            get() = "Not connected to internet;\nCheck your WIFI or Mobile Data!"
    }
}