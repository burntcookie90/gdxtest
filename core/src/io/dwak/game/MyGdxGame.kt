package io.dwak.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import io.dwak.game.ext.Position
import io.dwak.game.ext.addHorizontal
import io.dwak.game.ext.by

class MyGdxGame : ApplicationAdapter() {
  internal val batch: SpriteBatch by lazy { SpriteBatch() }
  internal val img: Texture by lazy { Texture("badlogic.jpg") }
  internal val stage: Stage by lazy { Stage(FitViewport(1280f, 720f)) }
  private val skin by lazy { Skin(Gdx.files.internal("default/uiskin.json")) }

  override fun create() {
    Gdx.input.inputProcessor = stage

    stage.addHorizontal(debug = true) {
      rowAlign = Align.top
      position = 800f by 600f
      vertical(align = Align.left) {
        horizontal {
          add(Label("Username", skin))
          add(TextField("", skin))
        }
        horizontal {
          add(Label("Password", skin))
          add(TextField("", skin))
        }
      }
      vertical {
        add(TextButton("Login", skin))
        add(TextButton("Exit", skin))
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
