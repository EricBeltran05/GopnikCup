package com.codefastly.gopnikcupapp.commons.bases

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.codefastly.gopnikcupapp.R


abstract class BaseActivity: AppCompatActivity() {

    private var loaderView: View? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        configureUI()
    }

    protected abstract fun configureUI()

    protected fun showLoading() {
        if (loaderView == null) {
            loaderView =
                LayoutInflater.from(this).inflate(R.layout.base_loader_layout, null)
            val rootView = findViewById<ViewGroup>(android.R.id.content)
            rootView.addView(loaderView)
        }
        loaderView?.visibility = View.VISIBLE
    }

    protected fun hideLoading() {
        loaderView?.visibility = View.GONE
    }

}