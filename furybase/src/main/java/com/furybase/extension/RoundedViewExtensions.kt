package com.furybase.extension

import android.content.res.TypedArray
import android.graphics.Path
import android.graphics.RectF
import android.view.View
import com.furybase.customviews.roundedViews.CornersHolder
import com.furybase.customviews.roundedViews.RoundOutlineProvider


/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


internal fun View.updateOutlineProvider(cornersHolder: CornersHolder) {
    outlineProvider = RoundOutlineProvider(cornersHolder)
}

internal fun View.updateOutlineProvider(cornerRadius: Float) {
    outlineProvider = RoundOutlineProvider(cornerRadius)
}

internal fun Path.addRoundRectWithRoundCorners(rectF: RectF, cornersHolder: CornersHolder) {
    addRoundRectWithRoundCorners(
        rectF,
        cornersHolder.topLeftCornerRadius,
        cornersHolder.topRightCornerRadius,
        cornersHolder.bottomRightCornerRadius,
        cornersHolder.bottomLeftCornerRadius
    )
}

internal fun Path.addRoundRectWithRoundCorners(
    rectF: RectF,
    topLeftCornerRadius: Float,
    topRightCornerRadius: Float,
    bottomRightCornerRadius: Float,
    bottomLeftCornerRadius: Float
) {
    addRoundRect(
        rectF,
        floatArrayOf(
            topLeftCornerRadius,
            topLeftCornerRadius,
            topRightCornerRadius,
            topRightCornerRadius,
            bottomRightCornerRadius,
            bottomRightCornerRadius,
            bottomLeftCornerRadius,
            bottomLeftCornerRadius
        ),
        Path.Direction.CW
    )
}

internal fun TypedArray.getCornerRadius(
    attrCornerRadius: Int,
    attrTopLeftCornerRadius: Int,
    attrTopRightCornerRadius: Int,
    attrBottomRightCornerRadius: Int,
    attrBottomLeftCornerRadius: Int
): CornersHolder {
    val cornerRadius = getDimension(attrCornerRadius, 0f)
    val topLeftCornerRadius = getDimension(attrTopLeftCornerRadius, cornerRadius)
    val topRightCornerRadius = getDimension(attrTopRightCornerRadius, cornerRadius)
    val bottomRightCornerRadius = getDimension(attrBottomRightCornerRadius, cornerRadius)
    val bottomLeftCornerRadius = getDimension(attrBottomLeftCornerRadius, cornerRadius)

    return CornersHolder(
        topLeftCornerRadius,
        topRightCornerRadius,
        bottomRightCornerRadius,
        bottomLeftCornerRadius
    )
}