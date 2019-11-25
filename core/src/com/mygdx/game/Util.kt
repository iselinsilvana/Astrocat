package com.mygdx.game

import com.badlogic.gdx.graphics.Camera
import kotlin.random.Random

fun Camera.hasVerticalOverlap(bottom: Float, top: Float): Boolean{
    val cameraTopY = position.y + viewportHeight / 2
    val cameraBottomY = position.y - viewportHeight / 2

    return (bottom in cameraBottomY..cameraTopY
            || top in cameraBottomY..cameraTopY
            || (bottom <= cameraBottomY && top >= cameraTopY))
}

fun randFloat(min: Float, max: Float): Float {
    return Random.Default.nextFloat() * (max - min) + min
}