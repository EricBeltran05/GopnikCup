package com.codefastly.gopnikcupapp.commons.coordinator.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.codefastly.gopnikcupapp.MainActivity
import com.codefastly.gopnikcupapp.commons.coordinator.BaseCoordinator

class BaseCoordinatorImpl: BaseCoordinator {
    override fun <T : Activity> startActivity(context: Context, activityClass: Class<T>, extras: Bundle?) {
        val intent = Intent(context, activityClass)
        extras?.let { intent.putExtras(it) }
        context.startActivity(intent, extras)
    }
}