package com.lsttsl.lsttslmemo

import android.app.Application
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapSdk
import io.realm.Realm

class DimoMemoApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        NaverMapSdk.getInstance(this).client=
            NaverMapSdk.NaverCloudPlatformClient("erzmvkluos")
    }

}