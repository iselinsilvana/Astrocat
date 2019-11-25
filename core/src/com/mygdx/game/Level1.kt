package com.mygdx.game

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

object Level1 {
    private const val coinNumber = 20
    private const val coinRadius = 25f

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

    val topY = backgroundElements.topY

    val coins: List<Coin> = (0..coinNumber).map {
        Coin(x = randFloat(0 + coinRadius, WIDTH_F - coinRadius),
                y = randFloat(0 + coinRadius, topY - coinRadius),
                radius = coinRadius,
                points = 10)
    }

    fun draw(camera: Camera, batch: SpriteBatch, shape: ShapeRenderer) {

        with(batch) {
            projectionMatrix = camera.combined
            begin()
            backgroundElements.draw(camera, batch)
            end()
        }

        coins.forEach {
            if (camera.hasVerticalOverlap(it.y - it.radius, it.y + it.radius)) {
                with(shape) {
                    projectionMatrix = camera.combined
                    begin(ShapeRenderer.ShapeType.Filled)
                    color = Color.YELLOW
                    circle(it.x, it.y, it.radius)
                    end()
                }
            }
        }
    }
}