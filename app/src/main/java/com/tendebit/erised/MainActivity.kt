package com.tendebit.erised

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import com.otaliastudios.cameraview.*

class MainActivity : AppCompatActivity() {
    companion object {

        private val MALE_CELEB_IMAGES_CATEGORY = DesireCategory( arrayOf(
                R.drawable.chris_pratt,
                R.drawable.the_rock,
                R.drawable.milo_ventimiglia,
                R.drawable.hugh_jackman,
                R.drawable.tom_hardy,
                R.drawable.channing_tatum,
                R.drawable.pizza,
                R.drawable.fried_chicken,
                R.drawable.fries,
                R.drawable.milkshake,
                R.drawable.chocolate,
                R.drawable.barack_obama
        ))

        private val FEMALE_CELEB_IMAGES_CATEGORY = DesireCategory( arrayOf(
                R.drawable.ariana_grande,
                R.drawable.the_rock,
                R.drawable.emma_watson,
                R.drawable.anna_kendrick,
                R.drawable.pizza,
                R.drawable.fried_chicken,
                R.drawable.fries,
                R.drawable.milkshake,
                R.drawable.chocolate,
                R.drawable.barack_obama
        ))


    }


    private lateinit var rootView: ViewGroup
    private lateinit var mDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)		// Hide the window title.
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.root)
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        findViewById<View>(R.id.overlay).setOnTouchListener { _, event ->
            mDetector.onTouchEvent(event)
        }
        val camera = findViewById<CameraView>(R.id.camera)
        camera.facing = Facing.FRONT
        camera.setLifecycleOwner(this)
        camera.mapGesture(Gesture.LONG_TAP, GestureAction.NONE)
        camera.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<CameraView>(R.id.camera).stop()
    }

    fun clearView() {
        rootView.removeAllViews()
    }


    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent) : Boolean {
          return true
        }

        override fun onLongPress(e: MotionEvent?) {
            clearView()
        }

        override fun onFling(
                event1: MotionEvent,
                event2: MotionEvent,
                velocityX: Float,
                velocityY: Float
        ): Boolean {
            clearView()

            val imageId = if (velocityX > 0) {
                MALE_CELEB_IMAGES_CATEGORY.getRandomImageId()
            } else {
                FEMALE_CELEB_IMAGES_CATEGORY.getRandomImageId()
            }
            val imageView = ImageView(this@MainActivity)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageDrawable(getDrawable(imageId))
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams.gravity = Gravity.BOTTOM
            imageView.layoutParams = layoutParams
            rootView.addView(imageView)
            return true
        }
    }

}
