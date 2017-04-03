package io.dwak.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.viewport.ScreenViewport

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
    }.also { stage.addActor(it) }

    val table = Table().apply {
      debug = true
      pad(16f, 16f, 16f, 16f)
    }
    val usernameLabel = Label("Username", skin)
    val nameText = TextField("", skin)
    table.add(usernameLabel)
    table.add(nameText).width(100f)

    val loginButton = TextButton("Login", skin)
    table.stack(loginButton).height(150f).width(200f)

    table.row()

    val passwordLabel = Label("Password", skin)
    val passwordText = TextField("", skin)
    table.add(passwordLabel)
    table.add(passwordText).width(100f)

    val verticalGroup = VerticalGroup()
    rootTable.add(table.right().top())
  }

  override fun render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
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
