package com.lsttsl.lsttslmemo

import android.content.Context
import android.location.Geocoder
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_info.view.*
import java.util.*

class LocationInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtter: Int = 0
) : InfoView(context, attrs, defStyleAtter) {

    init {
        typeImage.setImageResource(R.drawable.ic_location)
        infoText.text = ""
    }

    fun setLocation(latitude: Double, longitude: Double) {
        if (latitude == 0.0 && longitude == 0.0) {
            infoText.text = "위치정보가 없습니다."
        } else {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            infoText.text = addresses[0].adminArea
        }
    }

}