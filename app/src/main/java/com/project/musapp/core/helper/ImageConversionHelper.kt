package com.project.musapp.core.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File

object ImageConversionHelper {
    fun toByteArray(context: Context, imagePath: Uri): ByteArray {
    return when (imagePath.scheme) {
                "content" -> { //Imagen seleccionada desde un content provider.
                    context.contentResolver.openInputStream(imagePath)!!.use { inputStream ->
                        inputStream.readBytes()
                    }
                }

                else -> { //Imagen seleccionada desde el sistema de archivos.
                    val file = File(imagePath.path!!)
                    file.readBytes()
                }
            }

    }

    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}