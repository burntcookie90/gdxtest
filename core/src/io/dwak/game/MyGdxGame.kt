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
import io.dwak.game.ext.Position
import io.dwak.game.ext.addHorizontal

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

    stage.addHorizontal(debug = true) {
      rowAlign = Align.top
      position = Position(800f, 600f)
      vertical(align = Align.left) {
        horizontal {
          add(usernameLabel)
          add(usernameText)
        }
        horizontal {
          add(passwordLabel)
          add(passwordText)
        }
      }
      vertical {
        add(loginButton)
        add(exitButton)
      }
    }
  }

  override fun render() {
    Gdx.gl.apply {
      glClearColor(0f, 0f, 0f, 1f)
      glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
    batch.begin()
    stage.act(Gdx.graphics.deltaTime)
    stage.draw()
    batch.end()
  }

  override fun dispose() {
    batch.dispose()
    img.dispose()
    stage.dispose()
  }
}
