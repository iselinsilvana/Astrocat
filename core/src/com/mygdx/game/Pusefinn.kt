package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener


private const val PUSEFINN_SCALE = 0.5f
private const val SITTING_LEVEL = 150f

class Pusefinn : Actor() {

    enum class FinnState {
        SITTING, LAUNCHING, FALLING
    }

    private var state = FinnState.SITTING
    private var region = getTextureRegion()

    private var currentSpeed = 0f
    private val gravitationalConstant = -2000f

    private val touchListener = object : InputListener() {
        override fun touchDown(
            event: InputEvent?,
            x: Float,
            y: Float,
            pointer: Int,
            button: Int
        ): Boolean {
                playSound()
                giveExtraPush()
            return true
        }
    }

    init {
        resetPosition()
        val startingX: Float = (WIDTH_F/2) - (region.regionWidth/4)
        setBounds(startingX, 50f, region.regionWidth.toFloat() * PUSEFINN_SCALE, region.regionHeight.toFloat() * PUSEFINN_SCALE)
        listeners.add(touchListener)
    }

    private fun changeState() {
        state = when {
            currentSpeed < 0f -> FinnState.FALLING
            currentSpeed == 0f -> FinnState.SITTING
            currentSpeed > 0f -> FinnState.LAUNCHING
            else -> FinnState.SITTING
        }
    }

    private fun resetPosition() {
        state = FinnState.SITTING
        currentSpeed = 0f
    }

    private fun getTextureRegion(): TextureRegion = when (state) {
        FinnState.SITTING -> Assets.pusefinnSittingTexture
        FinnState.LAUNCHING -> Assets.pusefinnLaunchingTexture
        FinnState.FALLING -> Assets.pusefinnFallingTexture
    }

    fun giveExtraPush() {
        currentSpeed = 1500f
    }

    private fun getTranslation(delta: Float) {
        val newSpeed = currentSpeed + gravitationalConstant * delta
        val newY = y + 0.5f * (currentSpeed + newSpeed) * delta
        if (newY >= SITTING_LEVEL) {
            y = newY
            currentSpeed = newSpeed
        } else {
            y = SITTING_LEVEL
            currentSpeed = 0f
        }
    }

    fun playSound() {
        if (state == FinnState.SITTING) {
            Assets.launchSound?.play(0.5f)
        } else if (state == FinnState.FALLING) {
            Assets.bounceSound?.play(1.0f)
        }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        with(batch) {
            begin()
            region = getTextureRegion()
            batch.draw(region, x, y, originX, originY,
                width, height, scaleX, scaleY, rotation
            )
            end()
        }
    }

    override fun act(delta: Float) {
        changeState()
        getTranslation(delta)
        super.act(delta)
    }
}
