/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.vis.svgMapper.skia

import jetbrains.datalore.base.observable.property.WritableProperty
import jetbrains.datalore.base.registration.Registration
import jetbrains.datalore.mapper.core.Synchronizer
import jetbrains.datalore.mapper.core.SynchronizerContext
import jetbrains.datalore.mapper.core.Synchronizers
import jetbrains.datalore.vis.svg.SvgElement
import jetbrains.datalore.vis.svg.SvgElementListener
import jetbrains.datalore.vis.svg.event.SvgAttributeEvent
import jetbrains.datalore.vis.svg.event.SvgEventSpec
import jetbrains.datalore.vis.svgMapper.skia.drawing.Element

open class SvgElementMapper<SourceT : SvgElement, TargetT : Element>(
    source: SourceT,
    target: TargetT,
    peer: SvgSkiaPeer
) : SvgNodeMapper<SourceT, TargetT>(source, target, peer) {

    private var myHandlerRegs: MutableMap<SvgEventSpec, Registration>? = null


    open fun setTargetAttribute(name: String, value: Any?) {
        SvgUtils.setAttribute(target, name, value)
    }

    open fun applyStyle() {}

    override fun registerSynchronizers(conf: SynchronizersConfiguration) {
        super.registerSynchronizers(conf)

        conf.add(object : Synchronizer {
            private var myReg: Registration? = null

            override fun attach(ctx: SynchronizerContext) {
                applyStyle()

                myReg = source.addListener(object : SvgElementListener {
                    override fun onAttrSet(event: SvgAttributeEvent<*>) {
                        setTargetAttribute(event.attrSpec.name, event.newValue)
                    }

                })

                for (key in source.attributeKeys) {
                    val name = key.name
                    val value = source.getAttribute(name).get()
                    setTargetAttribute(name, value)
                }
            }

            override fun detach() {
                myReg!!.remove()
            }
        })

        conf.add(
            Synchronizers.forPropsOneWay(
                source.handlersSet(),
                object : WritableProperty<Set<SvgEventSpec>?> {
                    override fun set(value: Set<SvgEventSpec>?) {
                        if (myHandlerRegs == null) {
                            myHandlerRegs = HashMap()
                        }

                        for (spec in SvgEventSpec.values()) {
                            if (!value!!.contains(spec) && myHandlerRegs!!.containsKey(spec)) {
                                myHandlerRegs!!.remove(spec)!!.remove()
                            }
                            if (!value.contains(spec) || myHandlerRegs!!.containsKey(spec)) continue

                            when (spec) {
                                //SvgEventSpec.MOUSE_CLICKED -> addMouseHandler(spec, MouseEventFx.MOUSE_CLICKED)
                                //SvgEventSpec.MOUSE_PRESSED -> addMouseHandler(spec, MouseEventFx.MOUSE_PRESSED)
                                //SvgEventSpec.MOUSE_RELEASED -> addMouseHandler(spec, MouseEventFx.MOUSE_RELEASED)
                                //SvgEventSpec.MOUSE_OVER -> addMouseHandler(spec, MouseEventFx.MOUSE_ENTERED)
                                //SvgEventSpec.MOUSE_MOVE -> addMouseHandler(spec, MouseEventFx.MOUSE_MOVED)
                                //SvgEventSpec.MOUSE_OUT -> addMouseHandler(spec, MouseEventFx.MOUSE_EXITED)
                                else -> {
                                }
                            }
                        }

                        if (myHandlerRegs!!.isEmpty()) {
                            myHandlerRegs = null
                        }
                    }
                })
        )
    }
}
