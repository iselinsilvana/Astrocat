package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.assets.load


object Assets {
    //bakgrunn
    //background region?
    //Texture items
    //texture region for each background image
    //Animation jump

    //Sound jumpSound
    //coin sound
    //launchSound
    //crash sound
    var background: Texture? = null
    var backgroundRegion: TextureRegion? = null
    var puseFinnFalling: Sprite? = null
    var puseFinnSitting: Sprite? = null
    var puseFinnLaunching: Sprite? = null

    const val LIVING_ROOM_HEIGHT_F = 1372f

    private var puseFinnTextureAtlas: TextureAtlas? = null


    // TODO make this async
    fun load(assetManager: AssetManager){
        assetManager.load<Texture>("livingroom.png")
        assetManager.finishLoading()
        background = assetManager.get("livingroom.png", Texture::class.java)
        backgroundRegion = TextureRegion(background, 0, 0, WIDTH, LIVING_ROOM_HEIGHT_F.toInt())

        val puseFinnTextureAtlas = TextureAtlas("sprites.txt")
        puseFinnFalling = puseFinnTextureAtlas.createSprite("pus_KattGoingDown")
        puseFinnSitting = puseFinnTextureAtlas.createSprite("pus_KattSit")
        puseFinnLaunching = puseFinnTextureAtlas.createSprite("pus_KattGoingUp")
    }

    fun dispose(){
        background = null
        backgroundRegion = null
        puseFinnFalling = null
        puseFinnSitting = null
        puseFinnLaunching = null
        puseFinnTextureAtlas?.dispose()
        puseFinnTextureAtlas = null
    }

}