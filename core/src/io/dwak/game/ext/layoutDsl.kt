package io.dwak.game.ext

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Align

@DslMarker
annotation class GroupMarker

fun Stage.addGroup(group: Group) {
  group.group.setPosition(group.position.x, group.position.y)
  if (group.isHorizontal) {
    (group.group as HorizontalGroup).rowAlign((group as Horizontal).rowAlign)
  }
  addActor(group.group)
}

data class Position(val x: Float, val y: Float)
infix fun Float.by(other: Float) = Position(this, other)

@GroupMarker
abstract class Group(debug: Boolean, val isHorizontal: Boolean) {
  abstract val group: WidgetGroup
  var position: Position = Position(0f, 0f)
}

class Vertical(val debug: Boolean) : Group(debug, false) {
  override val group: VerticalGroup = VerticalGroup().apply { debug = this@Vertical.debug }

  fun add(actor: Actor) {
    group.addActor(actor)
  }

  fun vertical(align: Int = Align.center, f: Vertical.() -> Unit): Group {
    val v = Vertical(debug)
    f.invoke(v)
    this.group.align(align)
    this.group.addActor(v.group)
    return v
  }

  fun horizontal(align: Int = Align.center, f: Horizontal.() -> Unit): Group {
    val h = Horizontal(debug)
    f.invoke(h)
    this.group.align(align)
    this.group.addActor(h.group)
    return h
  }
}

class Horizontal(val debug: Boolean) : Group(debug, true) {
  override val group: HorizontalGroup = HorizontalGroup().apply { debug = this@Horizontal.debug }
  var rowAlign: Int = Align.center

  fun add(actor: Actor) {
    group.addActor(actor)
  }

  fun vertical(align: Int = Align.center, f: Vertical.() -> Unit): Group {
    val v = Vertical(debug)
    f.invoke(v)
    this.group.align(align)
    this.group.addActor(v.group)
    return v
  }

  fun horizontal(align: Int = Align.center, f: Horizontal.() -> Unit): Group {
    val h = Horizontal(debug)
    f.invoke(h)
    h.group.rowAlign(rowAlign)
    this.group.align(align)
    this.group.addActor(h.group)
    return h
  }
}

fun Stage.addVertical(debug: Boolean = false, align: Int = Align.center, f: Vertical.() -> Unit): Vertical {
  return Vertical(debug).also { f.invoke(it) }.also {
    addGroup(it)
  }
}

fun Stage.addHorizontal(debug: Boolean = false, align: Int = Align.center, f: Horizontal.() -> Unit): Horizontal {
  return Horizontal(debug).also {
    f.invoke(it)
    it.group.align(align)
    addGroup(it)
  }
}
