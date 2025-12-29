package com.jslps.birsabasistha.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import android.util.Base64

fun getCachedImageByName(context: Context, fileName: String): Uri? {
    val cacheDir = File(context.cacheDir, "images") // Ensure it matches save path
    val file = File(cacheDir, fileName)
    return if (file.exists()) {
        Log.d("ImageRetrieve", "Image found at: ${file.absolutePath}") // Debug Log
        Uri.fromFile(file)
    } else {
        Log.e("ImageRetrieve", "Image NOT found: ${file.absolutePath}") // Debug Log
        null
    }
}

var fileName = ""
var file: File? = null
fun createImageUri(context: Context): Uri {
    fileName = "UPAJ-VERI_${System.currentTimeMillis()}"
    file = File(context.cacheDir, fileName)
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file!!)
    return uri
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun byteArrayToBase64(byteArray: ByteArray): String {
    val bse64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}