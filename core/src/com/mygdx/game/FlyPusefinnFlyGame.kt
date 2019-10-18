package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ObjectMap
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.app.emptyScreen

class FlyPusefinnFlyGame<ScreenType: Screen>(firstScreen: ScreenType? = null, private val clearScreen: Boolean = true)
    : KtxApplicationAdapter {

    /** Holds references to all screens registered with [addScreen]. Allows to get a reference of the screen instance
     * knowing only its type. */
    protected val screens: ObjectMap<Class<out ScreenType>, ScreenType> = ObjectMap()

    /** Currently shown screen. Unless overridden with [setScreen], uses an empty mock-up implementation to work around
     * nullability and `lateinit` issues. [shownScreen] is a public property exposing this value as [ScreenType].
     * @see shownScreen */
    protected val currentScreen: Screen = firstScreen ?: emptyScreen()

    lateinit var batch: SpriteBatch
    lateinit var img: Texture


    private val GAME_READY = 1
    private val GAME_RUNNING = 2
    private val GAME_OVER = 3

    private var state: Int? = null

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        if (clearScreen) {
            clearScreen(0f, 0f, 0f, 1f)
        }
        currentScreen.render(Gdx.graphics.deltaTime)
    }

    inline fun clearScreen(red: Float, green: Float, blue: Float, alpha: Float = 1f) {
        Gdx.gl.glClearColor(red, green, blue, alpha)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
    override fun dispose() {
        batch.dispose()
        img.dispose()
    }
}