package com.nguyen.loadingindicator

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator


class LoadingView: View {

    companion object {
        const val DEFAULT_STROKE = 4
    }

    private val colors = intArrayOf(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED)

    lateinit var paint: Paint

    lateinit var shader: Shader

    private var stroke = DEFAULT_STROKE

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView)
        stroke =
            typeArray.getDimensionPixelSize(R.styleable.LoadingView_loading_stroke, DEFAULT_STROKE)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint.strokeWidth = stroke.toFloat()
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        rotate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 0
        var height = 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if(widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
        } else if(widthMode == MeasureSpec.AT_MOST) {
            width = widthSize
        } else {
            width = 0
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize
        } else if(widthMode == MeasureSpec.AT_MOST) {
            height = heightSize
        } else {
            height = 0
        }
        var radius = if(width < height) width else height
        setMeasuredDimension(radius, radius)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        shader = SweepGradient((width / 2).toFloat(), (height / 2).toFloat(), colors, null)
        paint.shader = shader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(),
            (width / 2 - stroke).toFloat(), paint)
    }

    fun rotate() {
        val anim = ObjectAnimator.ofFloat(rootView, View.ROTATION, 0.0f, 360.0f)
        anim.duration = 1000
        anim.repeatCount = Animation.INFINITE
        anim.interpolator = LinearInterpolator()
        anim.start()
    }
}