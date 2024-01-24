package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener


private const val PUSEFINN_W_F = 535f
private const val PUSEFINN_SCALE = 0.5f

class Pusefinn : Actor() {

    enum class FinnState {
        SITTING, LAUNCHING, FALLING
    }

    var state = FinnState.SITTING
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
        width = PUSEFINN_W_F
        height = 781f
        setBounds((WIDTH_F / 2f) - (PUSEFINN_W_F / 2f), y, width, height)
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

    fun resetPosition() {
        state = FinnState.SITTING
        currentSpeed = 0f
    }

    private fun getSprite(): Sprite? = when (state) {
        FinnState.SITTING -> Assets.puseFinnSitting
        FinnState.LAUNCHING -> Assets.puseFinnLaunching
        FinnState.FALLING -> Assets.puseFinnFalling
    }

    fun giveExtraPush() {
        currentSpeed = 1500f
    }

    private fun getTranslation(delta: Float) {
        val newSpeed = currentSpeed + gravitationalConstant * delta
        val newY = y + 0.5f * (currentSpeed + newSpeed) * delta
        if (newY >= 0f) {
            y = newY
            currentSpeed = newSpeed
        } else {
            y = 0f
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
            getSprite()?.let {
                it.setScale(PUSEFINN_SCALE)
                it.setPosition(x, y)
                it.draw(batch)
            }
            end()
        }
    }

    override fun act(delta: Float) {
        changeState()
        getTranslation(delta)
        super.act(delta)
    }
}
