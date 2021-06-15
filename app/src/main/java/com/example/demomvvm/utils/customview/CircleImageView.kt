package com.example.demomvvm.utils.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        isFilterBitmap = true
        style = Paint.Style.FILL
    }
    private var paintBorder: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.CYAN
        strokeWidth = STROKE_WIDTH
    }
    private var matrixImage: Matrix = Matrix()
    private var radius = 0f
    private var imageWidth: Int = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        imageWidth = min(measuredWidth, measuredHeight)
        radius = imageWidth / 2f
        setMeasuredDimension(imageWidth, imageWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        drawable?.let {
            setUpShader()
            canvas?.drawCircle(radius, radius, radius, paint)
            canvas?.drawCircle(radius , radius, radius + STROKE_WIDTH, paintBorder)
        }
    }

    private fun setUpShader() {
        drawable?.let {
            val bmp = drawToBitmap(it)
            val bitmapShader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            val size = min(bmp.width, bmp.height)
            val scale = imageWidth * 1f / size
            matrixImage.setScale(scale, scale)
            bitmapShader.setLocalMatrix(matrixImage)
            paint.setShader(bitmapShader)
        }
    }

    private fun drawToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) return drawable.bitmap
        val width = drawable.bounds.width()
        val height = drawable.bounds.height()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }

    companion object {
        private const val STROKE_WIDTH = 5f
    }
}
