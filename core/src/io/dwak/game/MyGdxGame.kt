package io.dwak.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport

class MyGdxGame : ApplicationAdapter() {
  internal lateinit var batch: SpriteBatch
  internal lateinit var img: Texture
  internal val stage: Stage by lazy { Stage(FitViewport(1280f, 720f)) }
  private val skin by lazy { Skin(Gdx.files.internal("default/uiskin.json")) }

  override fun create() {
    Gdx.input.inputProcessor = stage
    batch = SpriteBatch()
    img = Texture("badlogic.jpg")

    val usernameLabel = Label("Username", skin)
    val usernameText = TextField("", skin)

    val loginButton = TextButton("Login", skin)
    val exitButton = TextButton("Exit", skin)

    val passwordLabel = Label("Password", skin)
    val passwordText = TextField("", skin)

    val usernameGroup = HorizontalGroup().apply {
      debug = true
      addActor(usernameLabel)
      addActor(usernameText)
      align(Align.center)
    }
    val passwordGroup = HorizontalGroup().apply {
      debug = true
      addActor(passwordLabel)
      addActor(passwordText)
      align(Align.center)
    }

    val buttonGroup = VerticalGroup().apply {
      debug = true
      addActor(loginButton)
      addActor(exitButton)
    }
    val inputGroup = VerticalGroup().apply {
      debug = true
      addActor(usernameGroup)
      addActor(passwordGroup)
      align(Align.left)
    }

    val uiGroup = HorizontalGroup().apply {
      addActor(inputGroup)
      addActor(buttonGroup)
      rowAlign(Align.top)
      setPosition(800f, 600f)
    }
    stage.addActor(uiGroup)
//    stage.addActor(buttonGroup)
//    rootTable.add(table.right().top())
  }

  override fun render() {
    Gdx.gl.apply {
      glClearColor(0f, 0f, 0f, 1f)
      glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
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
