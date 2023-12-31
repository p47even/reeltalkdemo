package com.p47.reeltalk

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class Welcome1 : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome1)

        gestureDetector = GestureDetectorCompat(this, SwipeGestureListener())

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    //Swipe between screens
    private inner class SwipeGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        @Suppress("NOTHING_TO_OVERRIDE", "ACCIDENTAL_OVERRIDE")
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e2!!.x - e1!!.x
            val diffY = e2.y - e1.y

            if (abs(diffX) > abs(diffY) &&
                abs(diffX) > SWIPE_THRESHOLD &&
                abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (diffX <= 0) { //Next
                    startActivity(Intent(this@Welcome1, Welcome2::class.java))
                }
                return true
            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

}
