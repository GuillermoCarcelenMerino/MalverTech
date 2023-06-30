package com.example.marvelapplication.utils

import java.security.MessageDigest

class MarvelRequestGenerator private constructor() {

    companion object {
        fun createParams(): MarvelRequestGenerator {
            val generator = MarvelRequestGenerator()
            generator.setRequestParams()
            return generator
        }
    }

    val apiKey: String = com.example.marvelapplication.BuildConfig.PUBLIC_KEY
    val privateKey: String = com.example.marvelapplication.BuildConfig.PRIVATE_KEY
    var timestamp: Long? = null
    var hash: String? = null

    private fun setRequestParams() {
        timestamp = System.currentTimeMillis()
        val currentHash: String = timestamp.toString() + privateKey + apiKey
        hash = hashString(currentHash, "MD5")
    }
}

fun hashString(input: String, algorithm: String): String {
    val bytes = MessageDigest.getInstance(algorithm).digest(input.toByteArray())
    val result = StringBuilder()

    for (byte in bytes) {
        // Convierte cada byte a su representación hexadecimal de 2 dígitos
        result.append(String.format("%02x", byte))
    }

    return result.toString()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}
