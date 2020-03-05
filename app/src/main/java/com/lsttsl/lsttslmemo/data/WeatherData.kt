package com.lsttsl.lsttslmemo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

class WeatherData {
    companion object{
        private val xmlPullParserFactory by lazy { XmlPullParserFactory.newInstance() }
        suspend fun getCurrentWeather (latitude: Double, longitude: Double): String{
            return GlobalScope.async(Dispatchers.IO) {

                val requestUrl = "https://api.openweathermap.org/data/2.5/weather" +
                        "?lat=${latitude}&lon=${longitude}&mode=xml&units=metric&" +
                        "&appid=api_key"
                var currentWeather = ""
                try {
                    val url = URL(requestUrl)
                    val stream = url.openStream()

                    val parser = xmlPullParserFactory.newPullParser()
                    parser.setInput(InputStreamReader(stream, "UTF-8"))

                    var eventType = parser.eventType
                    var currentWeatherCode = 0

                    while (eventType!= XmlPullParser.END_DOCUMENT){
                        if (eventType == XmlPullParser.START_TAG && parser.name =="weather"){
                            currentWeatherCode = parser.getAttributeValue(null, "number").toInt()
                            break
                        }
                        eventType =parser.next()
                    }

                    currentWeather = when(currentWeatherCode){
                        in 200..299 -> "뇌우"
                        in 300..399 -> "이슬비"
                        in 500..599 -> "비"
                        in 600..699 -> "눈"
                        in 700..799 -> "안개"
                        771 -> "돌풍"
                        781-> "토네이도"
                        800 -> "맑음"
                        in  801..802 -> "구름조금"
                        in 803..804 -> "구름많음"
                        else -> ""
                    }

                }catch (e: Exception){
                    println(e)
                }

                currentWeather


            }.await()

        }
    }
}