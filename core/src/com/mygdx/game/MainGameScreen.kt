package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.mygdx.game.Assets.LIVING_ROOM_HEIGHT_F
import ktx.app.KtxScreen

// Width in world units
const val WIDTH = 750
const val WIDTH_F = WIDTH.toFloat()

// Supported aspect ratios: https://stackoverflow.com/a/28240009
const val MIN_RATIO = 4f / 3f
const val MAX_RATIO = 19.5f / 9f

const val MIN_HEIGHT_F = WIDTH_F * MIN_RATIO
const val MAX_HEIGHT_F = WIDTH_F * MAX_RATIO

const val MIN_HEIGHT = MIN_HEIGHT_F.toInt()
const val MAX_HEIGHT = MAX_HEIGHT_F.toInt()

class MainGameScreen(val game: Game) : KtxScreen {
    //I denne klassen foregår ca alt. Det er tre tilstander, før spelet begynner(katten sitter på bakken), i lufta mens spelet pågår
    //og etter at katten har krasja og me har tapt

    //for å tape: pusefinn er under høgda til skjermen, negativ y-verdi. då utløser me funksjonen game over


    fun gameOver() {
        //vis ein skjerm/popupmeny som viser score og knapper for å prøve igjen

    }





    private val batch = SpriteBatch()
    private val pusefinn = Pusefinn()

    private var hughscore: String? = null

    private val widthPx : Float get() = Gdx.graphics.width.toFloat()
    private val heightPx : Float get() = Gdx.graphics.height.toFloat()

    private val actualRatio : Float get() = MathUtils.clamp(heightPx / widthPx, MIN_RATIO, MAX_RATIO)
    private val height : Float get() =  WIDTH_F * actualRatio

    private val camera = OrthographicCamera(WIDTH_F, WIDTH_F * actualRatio).apply {
        position.set(viewportWidth / 2f, viewportHeight / 2f, 0f)
    }

    private val viewport = ExtendViewport(WIDTH_F, MIN_HEIGHT_F, WIDTH_F, MAX_HEIGHT_F, camera)

    private var gameOver = false

    private val shape = ShapeRenderer()

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

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

        game.batch.draw(Assets.backgroundRegion, 0f, 0f, WIDTH_F, LIVING_ROOM_HEIGHT_F)
        pusefinn.getSprite()?.let {
            it.setPosition(pusefinn.x, pusefinn.y)
            it.draw(game.batch)
        }

        game.batch.end()


        // This is the world
        shape.projectionMatrix = camera.combined
        shape.begin(ShapeRenderer.ShapeType.Line)
        shape.color = Color.RED
        shape.rect(0f, 0f, WIDTH_F - 1, height - 1)
        shape.end()
    }


    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}