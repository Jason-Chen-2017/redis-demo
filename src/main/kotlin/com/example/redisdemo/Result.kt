package com.example.redisdemo

import java.io.Serializable

class Result<T> : Serializable {
    var data: T? = null
    var msg: String? = null
    var success: Boolean = false

    constructor()
    constructor(data: T?, msg: String?, success: Boolean) {
        this.data = data
        this.msg = msg
        this.success = success
    }

    override fun toString(): String {
        return "Result(data=$data, msg=$msg, success=$success)"
    }
}
