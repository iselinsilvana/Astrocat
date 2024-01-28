package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
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
    var livingRoom: Texture? = null
    var livingRoomRegion: TextureRegion? = null

    var sky1: Texture? = null
    var sky1Region: TextureRegion? = null

    var sky2Gradient: Texture? = null
    var sky2GradientRegion: TextureRegion? = null

    var sky3: Texture? = null
    var sky3Region: TextureRegion? = null

    var sky4Gradient: Texture? = null
    var sky4GradientRegion: TextureRegion? = null

    var sky5: Texture? = null
    var sky5Region: TextureRegion? = null

    var sky6: Texture? = null
    var sky6Region: TextureRegion? = null

    var sky7Earth: Texture? = null
    var sky7EarthRegion: TextureRegion? = null

    var sky8: Texture? = null
    var sky8Region: TextureRegion? = null

    private val pusefinnTextureAtlas: TextureAtlas = TextureAtlas("sprites.txt")

    val pusefinnFallingTexture: TextureRegion = pusefinnTextureAtlas.findRegion("pus_KattGoingDown")
    val pusefinnSittingTexture: TextureRegion = pusefinnTextureAtlas.findRegion("pus_KattSit")
    val pusefinnLaunchingTexture: TextureRegion = pusefinnTextureAtlas.findRegion("pus_KattGoingUp")

    const val LIVING_ROOM_HEIGHT_F = 1372f
    const val SKY_HEIGHT_F = 1624f
    const val WIDTH_INT = 750

    var launchSound: Sound? = null
    var bounceSound: Sound? = null

    // TODO make this async
    fun load(assetManager: AssetManager){
        with(assetManager){
            load<Texture>("livingroom.png")
            load<Texture>("sky1.png")
            load<Texture>("sky2_gradient.png")
            load<Texture>("sky3.png")
            load<Texture>("sky4_gradient.png")
            load<Texture>("sky5.png")
            load<Texture>("sky6.png")
            load<Texture>("sky7_earth.png")
            load<Texture>("sky8.png")
            load<Sound>("launch.wav")
            load<Sound>("bounce.wav")
            finishLoading()
        }

        livingRoom = assetManager.get("livingroom.png", Texture::class.java)
        livingRoomRegion = TextureRegion(livingRoom, 0, 0, WIDTH_INT, LIVING_ROOM_HEIGHT_F.toInt())
        
        sky1 = assetManager.get("sky1.png", Texture::class.java)
        sky1Region = TextureRegion(sky1, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky2Gradient = assetManager.get("sky2_gradient.png", Texture::class.java)
        sky2GradientRegion = TextureRegion(sky2Gradient, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky3 = assetManager.get("sky3.png", Texture::class.java)
        sky3Region = TextureRegion(sky3, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky4Gradient = assetManager.get("sky4_gradient.png", Texture::class.java)
        sky4GradientRegion = TextureRegion(sky4Gradient, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky5 = assetManager.get("sky5.png", Texture::class.java)
        sky5Region = TextureRegion(sky5, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky6 = assetManager.get("sky6.png", Texture::class.java)
        sky6Region = TextureRegion(sky6, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky7Earth = assetManager.get("sky7_earth.png", Texture::class.java)
        sky7EarthRegion = TextureRegion(sky7Earth, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        sky8 = assetManager.get("sky8.png", Texture::class.java)
        sky8Region = TextureRegion(sky8, 0, 0, WIDTH_INT, SKY_HEIGHT_F.toInt())

        launchSound = assetManager.get("launch.wav", Sound::class.java)
        bounceSound = assetManager.get("bounce.wav", Sound::class.java)
    }

    fun dispose(){
        livingRoom = null
        livingRoomRegion = null

        sky1 = null
        sky1Region = null

        sky2Gradient = null
        sky2GradientRegion = null

        sky3 = null
        sky3Region = null

        sky4Gradient = null
        sky4GradientRegion = null

        sky5 = null
        sky5Region = null

        sky6 = null
        sky6Region = null

        sky7Earth = null
        sky7EarthRegion = null

        sky8 = null
        sky8Region = null

        pusefinnTextureAtlas.dispose()

        launchSound?.dispose()
        launchSound = null

        bounceSound?.dispose()
        bounceSound = null
    }

}