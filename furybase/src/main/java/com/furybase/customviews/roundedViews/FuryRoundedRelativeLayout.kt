package com.furybase.customviews.roundedViews

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.furybase.R
import com.furybase.extension.getCornerRadius
import com.furybase.extension.updateOutlineProvider

class FuryRoundedRelativeLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr) {
    private val canvasRounder: CanvasRounder

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerRelativeLayout, 0, 0)

        val cornersHolder = array.getCornerRadius(
                R.styleable.RoundCornerRelativeLayout_corner_radius,
                R.styleable.RoundCornerRelativeLayout_top_left_corner_radius,
                R.styleable.RoundCornerRelativeLayout_top_right_corner_radius,
                R.styleable.RoundCornerRelativeLayout_bottom_right_corner_radius,
                R.styleable.RoundCornerRelativeLayout_bottom_left_corner_radius
        )

        array.recycle()
        canvasRounder = CanvasRounder(cornersHolder)
        updateOutlineProvider(cornersHolder)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
    }

    override fun onSizeChanged(currentWidth: Int, currentHeight: Int, oldWidth: Int, oldheight: Int) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldheight)
        canvasRounder.updateSize(currentWidth, currentHeight)
    }

    override fun draw(canvas: Canvas) = canvasRounder.round(canvas) { super.draw(it) }

    override fun dispatchDraw(canvas: Canvas) = canvasRounder.round(canvas) { super.dispatchDraw(it)}

    fun setCornerRadius(cornerRadius: Float, cornerType: CornerType = CornerType.ALL) {
        when (cornerType) {
            CornerType.ALL -> {
                canvasRounder.cornerRadius = cornerRadius
            }
            CornerType.TOP_LEFT -> {
                canvasRounder.topLeftCornerRadius = cornerRadius
            }
            CornerType.TOP_RIGHT -> {
                canvasRounder.topRightCornerRadius = cornerRadius
            }
            CornerType.BOTTOM_RIGHT -> {
                canvasRounder.bottomRightCornerRadius = cornerRadius
            }
            CornerType.BOTTOM_LEFT -> {
                canvasRounder.bottomLeftCornerRadius = cornerRadius
            }
        }
        updateOutlineProvider(cornerRadius)
        invalidate()
    }
}