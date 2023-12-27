package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor


private const val PUSEFINN_W_F = 535f
private const val PUSEFINN_SCALE = 0.5f

class Pusefinn: Actor() {

    enum class FinnState {
        SITTING, LAUNCHING, FALLING
    }

    var state = FinnState.SITTING
    private var currentSpeed = 0f
    private val gravitationalConstant = -2000f

   init {
        resetPosition()
    }

    fun resetPosition() {
        height = PUSEFINN_W_F
        width = 781f
        x = (WIDTH_F / 2f) - (PUSEFINN_W_F / 2f)
        y = 0f
        state = FinnState.SITTING
        currentSpeed = 0f
    }

    private fun getSprite(): Sprite? = when(state){
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
            else ->  {
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

    fun isTouchingCat(inputX: Int, inputY: Int) {
        val isWithinXRange = inputX >= x && inputX <= (x + width)
        val isWithinYRange = inputY >= y && inputY <= (y + height)
        val isTouching = isWithinXRange && isWithinYRange
        println("height: $height, width: $ $width")
        println("W_F: $PUSEFINN_W_F")
        println(" input x: $inputX, x: $x, x + width: ${x + PUSEFINN_W_F}. input y: $inputY, y: $y, y + height: ${y + 500f}. is touching cat: $isTouching")
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
}
