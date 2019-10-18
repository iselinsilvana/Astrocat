package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.TextureAtlas


const val PUSEFINN_W_F = 535f
const val PUSEFINN_H_F = 781f

class Pusefinn: Actor() {

    enum class FinnState {
        SITTING, LAUNCHING, FALLING
    }

    var state = FinnState.SITTING

   init {
        x = (WIDTH_F / 2f) - (PUSEFINN_W_F / 2f)
        y = 0f
    }

    fun getSprite(): Sprite? = when(state){
        FinnState.SITTING -> Assets.puseFinnSitting
        FinnState.LAUNCHING -> Assets.puseFinnLaunching
        FinnState.FALLING -> Assets.puseFinnFalling
    }
}
