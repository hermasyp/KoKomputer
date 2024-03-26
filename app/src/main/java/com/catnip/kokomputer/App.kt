package com.catnip.kokomputer

import android.app.Application
import com.catnip.kokomputer.data.source.local.AppDatabase

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}