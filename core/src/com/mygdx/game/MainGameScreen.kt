package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen


// Width in world units
const val WIDTH = 750
const val WIDTH_F = WIDTH.toFloat()

// Supported aspect ratios: https://stackoverflow.com/a/28240009
const val MIN_RATIO = 4f / 3f
const val MAX_RATIO = 19.5f / 9f

const val MIN_HEIGHT_F = WIDTH_F * MIN_RATIO
const val MAX_HEIGHT_F = WIDTH_F * MAX_RATIO

class MainGameScreen(val game: Game) : KtxScreen {
    //I denne klassen foregår ca alt. Det er tre tilstander, før spelet begynner(katten sitter på bakken), i lufta mens spelet pågår
    //og etter at katten har krasja og me har tapt

    //for å tape: pusefinn er under høgda til skjermen, negativ y-verdi. då utløser me funksjonen game over


    private fun gameOver() {
        //vis ein skjerm/popupmeny som viser score og knapper for å prøve igjen

        // foreløbig gjer eg sånn at eg berre resetter alt til start
        hasReleasedTouch = true
        pusefinn.let {
            it.state = Pusefinn.FinnState.SITTING
            it.resetPosition()
        }
        camera.apply {
            position.set(viewportWidth / 2f, viewportHeight / 2f, 0f)
        }
    }
    private val pusefinn = Pusefinn().apply { touchable = Touchable.enabled }

    private var hasReleasedTouch = true

    private var highScore: String? = null

    private val widthPx : Float get() = Gdx.graphics.width.toFloat()
    private val heightPx : Float get() = Gdx.graphics.height.toFloat()

    private val actualRatio : Float get() = MathUtils.clamp(heightPx / widthPx, MIN_RATIO, MAX_RATIO)
    private val height : Float get() =  WIDTH_F * actualRatio

    private val camera = OrthographicCamera(WIDTH_F, WIDTH_F * actualRatio).apply {
        position.set(viewportWidth / 2f, viewportHeight / 2f, 0f)
    }

    private val viewport = ExtendViewport(WIDTH_F, MIN_HEIGHT_F, WIDTH_F, MAX_HEIGHT_F, camera)
    private val stage = Stage(viewport)

    private val shape = ShapeRenderer()

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(pusefinn)
    }

    private fun isCatBelowView() : Boolean = (pusefinn.y - (camera.position.y - height)) < 0f



    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        //må finne ein bedre plass for dette
        //val cameraTranslation = 5f
        //camera.translate(0f, cameraTranslation, 0f)
        stage.act()
        draw()
    }

    private fun draw(){
        camera.update()
        Level1.draw(camera, game.batch, shape)
        pusefinn.draw(game.batch, 1f)
    }
}