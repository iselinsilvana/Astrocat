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
            if (isTouchingCat(x, y)) {
                playSound()
                giveExtraPush()
            }

            /*
                        if (Gdx.input.isTouched) {
                            val inputX = Gdx.input.x
                            val inputY = Gdx.input.y
                            giveExtraPush()
                            *//*if (hasReleasedTouch) {
                    giveExtraPush()
                }
                hasReleasedTouch = false*//*
                state = FinnState.LAUNCHING
                val cameraTranslation = 5f
                //camera.translate(0f, cameraTranslation, 0f)
                *//*} else if (isCatBelowView()) {
                    MainGameScreen.gameOver()*//*
            } else if (y > 0f) {
                //hasReleasedTouch = true
                state = Pusefinn.FinnState.FALLING
            } else {
                //hasReleasedTouch = true
                state = Pusefinn.FinnState.SITTING
            }*/
            return true
        }
    }

    init {
        resetPosition()
        setBounds((WIDTH_F / 2f) - (PUSEFINN_W_F / 2f), 0f, PUSEFINN_W_F, 781f)
        listeners.add(touchListener)
    }

    private fun changeState() {
        if (y >= 0f) {
            state = when {
                currentSpeed < 0f -> FinnState.FALLING
                currentSpeed == 0f -> FinnState.SITTING
                currentSpeed > 0f -> FinnState.LAUNCHING
                else -> FinnState.SITTING
            }
        } else {
            currentSpeed = 0f
            state = FinnState.SITTING
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

    fun getTranslation(delta: Float) {
        when (state) {
            FinnState.SITTING -> y = 0f
            else -> {
                val newSpeed = currentSpeed + gravitationalConstant * delta
                y += 0.5f * (currentSpeed + newSpeed) * delta
                currentSpeed = newSpeed
            }
        }
    }

    fun playSound() {
        if (state == FinnState.SITTING) {
            Assets.launchSound?.play(0.5f)
        } else if (state == FinnState.FALLING) {
            Assets.bounceSound?.play(1.0f)
        }
    }

    fun isTouchingCat(inputX: Float, inputY: Float): Boolean {
        val isWithinXRange = inputX >= x && inputX <= (x + width / 2)
        val isWithinYRange = inputY >= y && inputY <= (y + height / 2)
        val isTouching = isWithinXRange && isWithinYRange
        println("height: $height, width: $ $width")
        println("W_F: $PUSEFINN_W_F")
        println(" input x: $inputX, x: $x, x + width: ${x + PUSEFINN_W_F}. input y: $inputY, y: $y, y + height: ${y + 500f}. is touching cat: $isTouching")
        return isTouching
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
