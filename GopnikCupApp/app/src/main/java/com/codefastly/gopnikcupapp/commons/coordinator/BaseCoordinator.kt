package com.codefastly.gopnikcupapp.commons.coordinator

import android.app.Activity
import android.content.Context
import android.os.Bundle

interface BaseCoordinator {
    fun <T : Activity> startActivity(context: Context, activityClass: Class<T>, extras: Bundle? = null)
}