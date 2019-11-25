package com.mygdx.game

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.Assets.LIVING_ROOM_HEIGHT_F
import com.mygdx.game.Assets.SKY_HEIGHT_F
import com.mygdx.game.Assets.livingRoomRegion
import com.mygdx.game.Assets.sky1Region
import com.mygdx.game.Assets.sky2GradientRegion
import com.mygdx.game.Assets.sky3Region
import com.mygdx.game.Assets.sky4GradientRegion
import com.mygdx.game.Assets.sky5Region
import com.mygdx.game.Assets.sky6Region
import com.mygdx.game.Assets.sky7EarthRegion
import com.mygdx.game.Assets.sky8Region

abstract class BackgroundElement() {
    abstract val width: Float
    abstract val height: Float
    val x = 0f
    var y = 0f
    abstract val asset: TextureRegion?

    fun draw(camera: Camera, batch: SpriteBatch) {
        asset?.let {
            val cameraTopY = camera.position.y + camera.viewportHeight / 2
            val cameraBottomY = camera.position.y - camera.viewportHeight / 2
            val bgBottomY = y
            val bgTopY = y + height

            if (bgBottomY in cameraBottomY..cameraTopY
                    || bgTopY in cameraBottomY..cameraTopY
                    || (bgBottomY <= cameraBottomY && bgTopY >= cameraTopY)) {
                batch.draw(it, x, y, width, height)
            }
        }
    }
}

object LivingRoom : BackgroundElement() {
    override val width = WIDTH_F
    override val height = LIVING_ROOM_HEIGHT_F
    override val asset = livingRoomRegion

}

abstract class Sky : BackgroundElement() {
    override val width = WIDTH_F
    override val height = SKY_HEIGHT_F
}


object Sky1 : Sky() {
    override val asset = sky1Region
}

object Sky2Gradient : Sky() {
    override val asset = sky2GradientRegion
}

object Sky3 : Sky() {
    override val asset = sky3Region
}

object Sky4Gradient : Sky() {
    override val asset = sky4GradientRegion
}

object Sky5 : Sky() {
    override val asset = sky5Region
}

object Sky6 : Sky() {
    override val asset = sky6Region
}

object Sky7Earth : Sky() {
    override val asset = sky7EarthRegion
}

object Sky8 : Sky() {
    override val asset = sky8Region
}

class BackgroundList(private val backgroundElements: List<BackgroundElement>) {
    init {
        var currentY = 0f

        backgroundElements.forEach {
            it.y = currentY
            currentY += it.height
        }
    }

    fun draw(camera: Camera, batch: SpriteBatch) {
        backgroundElements.forEach {
            it.draw(camera, batch)
        }
    }
}