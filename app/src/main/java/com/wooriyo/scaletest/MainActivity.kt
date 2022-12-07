package com.wooriyo.scaletest

import android.content.Context
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val density = resources.displayMetrics.density
        val strDensity = when {
            density >= 4.0 -> "xxxhdpi"
            density >= 3.0 -> "xxhdpi"
            density >= 2.0 -> "xhdpi"
            density >= 1.5 -> "hdpi"
            density >= 1.0 -> "mdpi"
            else -> "ldpi"
        }
        Log.d(TAG, "density (화면 밀도) >>> $density")
        Log.d(TAG, "코드 내에서 어떤 밀도 > dpi로 인식하는지...? >>> $strDensity")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics

            val newWidth = windowMetrics.bounds.width()
            val newHeight = windowMetrics.bounds.height()
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            val calWidth = newWidth - insets.bottom - insets.top
            val calHeight = newHeight - insets.bottom - insets.top

            Log.d(TAG, "버전 30 이상에서 metrics 넓이  >>> $newWidth")
            Log.d(TAG, "버전 30 이상에서 metrics 높이  >>> $newHeight")
            Log.d(TAG, "버전 30 이상에서 insets (navigation bar, status bar 등) 길이  >>> $insets")
            Log.d(TAG, "버전 30 이상에서 넓이에서 insets 뺀 값  >>> $calWidth")
            Log.d(TAG, "버전 30 이상에서 높이에서 insets 뺀 값  >>> $calHeight")
        }

        val display = windowManager.defaultDisplay
        val realpoint = Point()
        display.getRealSize(realpoint) // or getSize(size)
        val real_width = realpoint.x
        val real_height = realpoint.y

        val point = Point()
        display.getSize(point)
        val width = point.x
        val height = point.y

        Log.d(TAG, "모든 버전에서 real 넓이 (곧 deprecate 됨) >>> $real_width")
        Log.d(TAG, "모든 버전에서 real 길이 (곧 deprecate 됨) >>> $real_height")
        Log.d(TAG, "모든 버전에서 insets 뺀 width (곧 deprecate 됨)  >>> $width")
        Log.d(TAG, "모든 버전에서 insets 뺀 height (곧 deprecate 됨)  >>> $height")

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val metricsWidth = displayMetrics.widthPixels
        val metricsHeight = displayMetrics.heightPixels

        Log.d(TAG, "모든 버전에서 metrics 넓이 (곧 deprecate 됨) >>> $metricsWidth")
        Log.d(TAG, "모든 버전에서 metrics 길이 (곧 deprecate 됨) >>> $metricsHeight")

        val dpi = displayMetrics.densityDpi
        var strDpi = ""
        if (dpi<=160) { // mdpi
            strDpi = "mdpi"
        } else if (dpi<=240) { // hdpi
            strDpi = "hdpi"
        } else if (dpi<=320) { // xhdpi
            strDpi = "xhdpi"
        } else if (dpi<=480) { // xxhdpi
            strDpi = "xxhdpi"
        } else if (dpi<=640) { // xxxhdpi
            strDpi = "xxxhdpi"
        }
        Log.d(TAG, "dpi (Dot Per Inch - 화면 밀도 / 해상도) >>> $dpi")
        Log.d(TAG, "코드 내에서 어떤 dpi로 인식하는지 >>> $strDpi")

        val img : ImageView = findViewById<ImageView>(R.id.cat)
        img.measure(0, 0)
        val img_width = img.width
        val img_height = img.height
        val img_measure_width = img.measuredWidth
        val img_measure_height = img.measuredHeight

        Log.d(TAG, "이미지 넓이 >>> $img_width")
        Log.d(TAG, "이미지 높이 >>> $img_height")
        Log.d(TAG, "이미지 measure 넓이 >>> $img_measure_width")
        Log.d(TAG, "이미지 measure 높이 >>> $img_measure_height")
    }
}