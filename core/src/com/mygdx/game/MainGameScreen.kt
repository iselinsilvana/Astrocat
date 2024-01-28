package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen

// Width in world units
const val WIDTH = 750
const val WIDTH_F = WIDTH.toFloat()

const val WIDTH_WORLD_UNITS = 750f
const val HEIGHT_WORLD_UNITS = 1500f

class MainGameScreen(val game: Game) : KtxScreen {
    //I denne klassen foregår ca alt. Det er tre tilstander, før spelet begynner(katten sitter på bakken), i lufta mens spelet pågår
    //og etter at katten har krasja og me har tapt

    //for å tape: pusefinn er under høgda til skjermen, negativ y-verdi. då utløser me funksjonen game over

    private val pusefinn = Pusefinn().apply { touchable = Touchable.enabled }

    private val camera = OrthographicCamera(WIDTH_WORLD_UNITS, HEIGHT_WORLD_UNITS).apply {
        position.set(viewportWidth/2, viewportHeight/2, 0f)
    }

    private val viewport = ExtendViewport(WIDTH_WORLD_UNITS, HEIGHT_WORLD_UNITS, camera)

    private val stage = Stage(viewport)

    private val shape = ShapeRenderer()

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(pusefinn)
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
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