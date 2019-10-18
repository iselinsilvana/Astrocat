package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import ktx.app.KtxScreen

class MainGameScreen(val game: Game) : KtxScreen {
    //I denne klassen foregår ca alt. Det er tre tilstander, før spelet begynner(katten sitter på bakken), i lufta mens spelet pågår
    //og etter at katten har krasja og me har tapt

    //for å tape: pusefinn er under høgda til skjermen, negativ y-verdi. då utløser me funksjonen game over


    fun gameOver() {
        //vis ein skjerm/popupmeny som viser score og knapper for å prøve igjen

    }

    val width = Gdx.graphics.width.toFloat()
    val height = Gdx.graphics.height.toFloat()

    val batch = SpriteBatch().apply {

    }

    private var hughscore: String? = null
    private val camera = PerspectiveCamera()
    private val viewport = FillViewport(width, height, camera)
    private var gameOver = false

    override fun render(delta: Float) {
        camera.update()
        game.batch.projectionMatrix = camera.combined
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }

    //if (gameOver) gameOver()
}