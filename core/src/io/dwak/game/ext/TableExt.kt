package io.dwak.game.ext

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table

operator fun <T: Actor> Table.plusAssign(actor: T) {
  this.add(actor)
}
