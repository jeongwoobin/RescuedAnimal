package com.example.rescuedanimals.data.util

import java.io.IOException


class NoNetworkException : IOException() {
    override val message: String
        get() = "네트워크 연결이 원활하지 않습니다."
}
