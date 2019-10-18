package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import ktx.app.KtxScreen

const val WIDTH = 750
const val WIDTH_F = WIDTH.toFloat()

const val HEIGHT = 1372
const val HEIGHT_F = HEIGHT.toFloat()

class MainGameScreen(val game: Game) : KtxScreen {
    //I denne klassen foregår ca alt. Det er tre tilstander, før spelet begynner(katten sitter på bakken), i lufta mens spelet pågår
    //og etter at katten har krasja og me har tapt

    //for å tape: pusefinn er under høgda til skjermen, negativ y-verdi. då utløser me funksjonen game over


    fun gameOver() {
        //vis ein skjerm/popupmeny som viser score og knapper for å prøve igjen

    }

    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()

    private val batch = SpriteBatch()
    private val pusefinn = Pusefinn()

    private var hughscore: String? = null
    private val camera = OrthographicCamera(WIDTH_F, HEIGHT_F).apply {
        position.set((WIDTH / 2).toFloat(), (HEIGHT / 2).toFloat(), 0f)
    }
    private val viewport = FillViewport(width, height, camera)
    private var gameOver = false

    override fun render(delta: Float) {
        update(delta)
        draw()
    }

    private fun update(delta: Float) {
        if (Gdx.input.justTouched()) { // is called one time when screen is touched
            val pusVals = Pusefinn.FinnState.values()
            pusefinn.state = pusVals[(pusefinn.state.ordinal + 1) % pusVals.size]
        }
    }

    private fun draw(){
        camera.update()
        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.batch.draw(Assets.backgroundRegion, 0f, 0f, WIDTH_F, HEIGHT_F)
        pusefinn.getSprite()?.let {
            it.setPosition(pusefinn.x, pusefinn.y)
            it.draw(game.batch)
        }

        game.batch.end()
    }



    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}