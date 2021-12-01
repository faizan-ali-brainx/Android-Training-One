package com.brainx.androidext.ext


import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle


fun <T> Activity.startExtActivity(
    activityClass: Class<T>,
    activityOption: ActivityOptions? = null,
    isFinishAffinity: Boolean = false,
    isFinish: Boolean = false,
    intent: Intent? = null,
    bundle: Bundle? = null,
) {
    val newIntent = intent ?: Intent(this@startExtActivity, activityClass)
    bundle?.apply { newIntent.putExtras(bundle) }

    activityOption?.apply {
        startActivity(newIntent, toBundle())
    } ?: run {
        startActivity(newIntent)
    }
    if (isFinishAffinity) finishAffinity()
    if (isFinish && !isFinishAffinity) finish()
}






