package com.mygdx.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen

class Game : KtxGame<KtxScreen>() {
    lateinit var batch: SpriteBatch
    lateinit var assetManager: AssetManager

    override fun create() {
        assetManager = AssetManager()
        Assets.load(assetManager)
        batch = SpriteBatch()
        addScreen(MainGameScreen(this))
        setScreen<MainGameScreen>()
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
    }
}