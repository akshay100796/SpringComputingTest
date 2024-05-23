package com.example.springtak.utils.network

data class LoginReq(val email: String, val password: String)

data class LoginRes (val token: String)