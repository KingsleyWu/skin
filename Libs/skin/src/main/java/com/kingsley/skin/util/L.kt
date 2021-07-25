package com.kingsley.skin.util

import android.util.Log

/**
 * Created by kingsley on 2020/9/11
 */
object L {
    var DEBUG = true
    const val TAG = "L"

    @JvmOverloads
    fun i(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.i(tag, msg)
        }
    }

    @JvmOverloads
    fun v(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.v(tag, msg)
        }
    }

    @JvmOverloads
    fun d(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.d(tag, msg)
        }
    }

    @JvmOverloads
    fun w(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.w(tag, msg)
        }
    }

    @JvmOverloads
    fun e(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.e(tag, msg)
        }
    }

    @JvmOverloads
    fun i(tag: String = TAG, msg: String, e: Throwable) {
        if (DEBUG) {
            Log.i(tag, msg, e)
        }
    }

    @JvmOverloads
    fun v(tag: String = TAG, msg: String, e: Throwable) {
        if (DEBUG) {
            Log.v(tag, msg, e)
        }
    }

    @JvmOverloads
    fun d(tag: String = TAG, msg: String, e: Throwable) {
        if (DEBUG) {
            Log.d(tag, msg, e)
        }
    }

    fun w(tag: String = TAG, msg: String, e: Throwable) {
        if (DEBUG) {
            Log.w(tag, msg, e)
        }
    }

    fun e(tag: String = TAG, msg: String, e: Throwable) {
        if (DEBUG) {
            Log.e(tag, msg, e)
        }
    }

    @JvmOverloads
    fun w(tag: String = TAG, e: Throwable) {
        if (DEBUG) {
            Log.w(tag, e)
        }
    }

    @JvmOverloads
    fun e(tag: String = TAG, e: Throwable) {
        if (DEBUG) {
            Log.e(tag, Log.getStackTraceString(e))
        }
    }
}