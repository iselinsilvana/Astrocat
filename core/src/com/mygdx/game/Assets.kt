package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
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


    // TODO make this async
    fun load(assetManager: AssetManager){
        assetManager.load<Texture>("livingroom.png")
        assetManager.finishLoading()
        background = assetManager.get("livingroom.png", Texture::class.java)
        backgroundRegion = TextureRegion(background, 0, 0, WIDTH, HEIGHT)
    }

}