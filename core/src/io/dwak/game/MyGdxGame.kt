package io.dwak.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import io.dwak.game.ext.plusAssign

class MyGdxGame : ApplicationAdapter() {
  internal lateinit var batch: SpriteBatch
  internal lateinit var img: Texture
  internal lateinit var stage: Stage

  override fun create() {
    stage = Stage(ScreenViewport())
    Gdx.input.inputProcessor = stage
    batch = SpriteBatch()
    img = Texture("badlogic.jpg")

    val skin = Skin(Gdx.files.internal("default/uiskin.json"))

    val rootTable = Table().apply {
      setFillParent(true)
      debug = true
    }

    val table = Table().apply { debug = true }
    val label = Label("Name", skin)
    table += label

    rootTable.addActor(table)
    stage.addActor(rootTable)
  }

  override fun render() {
    Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    stage.act(Gdx.graphics.deltaTime)
    stage.draw()
//    batch.draw(img, 0f, 0f)
    batch.end()
  }

  override fun dispose() {
    batch.dispose()
    img.dispose()
    stage.dispose()
  }
}
