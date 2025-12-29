package com.jslps.empvisist.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.jslps.compose.utils.AppConstant
import com.jslps.empvisist.utils.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.timeout
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.contentType
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json

@Singleton
class NetworkHelper @Inject constructor(
    val client: HttpClient,
    @ApplicationContext private val context: Context
)
{
    
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        }
    }
    
    suspend inline fun <reified T> postFormUrlEncoded(
        apiMethod: String,
        params: Map<String, String>,
        parseJson: Boolean = true
    ): ApiResponse<T> {
        return try {
            // Check network connectivity first
            if (!isNetworkAvailable()) {
                return ApiResponse.Error("No internet connection")
            }
            
            val httpResponse: HttpResponse = client.post(AppConstant.BASE_URL + apiMethod) {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(FormDataContent(Parameters.build {
                    params.forEach { (key, value) ->
                        append(key, value)
                    }
                }))
                timeout {
                    requestTimeoutMillis = 30000
                    connectTimeoutMillis = 30000
                    socketTimeoutMillis = 30000
                }
            }
            
            if (httpResponse.status == HttpStatusCode.OK) {
                
                // Handle XML string wrapper if present
                val responseText = httpResponse.body<String>()
                val xmlResponse = responseText.substringAfter("<string xmlns=\"http://tempuri.org/\">")
                    .substringBefore("</string>")

                val finalResponse = if (parseJson) {
                    JsonParser.fromJson(xmlResponse, T::class.java)
                } else {
                    xmlResponse as T
                }

                Log.d("TAG", "postFormUrlEncoded: $finalResponse")
                
                ApiResponse.Success(finalResponse)
            } else {
                ApiResponse.Error("HTTP Error: ${httpResponse.status}")
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network request failed: ${e.message}", e)
        }
    }
}