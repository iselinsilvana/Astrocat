package com.mygdx.game

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

object Level1 {
    private val backgroundElements = BackgroundList(
            listOf(
                    LivingRoom,
                    Sky1,
                    Sky2Gradient,
                    Sky3,
                    Sky4Gradient,
                    Sky5,
                    Sky6,
                    Sky7Earth,
                    Sky8))

    fun draw(camera: Camera, batch: SpriteBatch) {
        backgroundElements.draw(camera, batch)
    }
}