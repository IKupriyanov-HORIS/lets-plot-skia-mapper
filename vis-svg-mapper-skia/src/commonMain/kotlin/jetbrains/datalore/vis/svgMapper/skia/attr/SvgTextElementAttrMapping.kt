/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.vis.svgMapper.skia.attr

import jetbrains.datalore.vis.svg.SvgConstants.SVG_TEXT_DY_CENTER
import jetbrains.datalore.vis.svg.SvgConstants.SVG_TEXT_DY_TOP
import jetbrains.datalore.vis.svg.SvgTextContent
import jetbrains.datalore.vis.svg.SvgTextElement
import jetbrains.datalore.vis.svgMapper.skia.drawable.Text

internal object SvgTextElementAttrMapping : SvgShapeMapping<Text>() {
    override fun setAttribute(target: Text, name: String, value: Any?) {
        when (name) {
            SvgTextElement.X.name -> target.x = value?.asFloat ?: 0.0f
            SvgTextElement.Y.name -> target.y = value?.asFloat ?: 0.0f
            SvgTextContent.TEXT_ANCHOR.name -> {
                val svgTextAnchor = value as String?
                revalidatePositionAttributes(svgTextAnchor, target)
            }
            SvgTextContent.TEXT_DY.name -> {
                when (value) {
                    SVG_TEXT_DY_TOP -> TODO() //target.textOrigin = VPos.TOP
                    SVG_TEXT_DY_CENTER -> TODO() //target.textOrigin = VPos.CENTER
                    else -> throw IllegalStateException("Unexpected text 'dy' value: $value")
                }
            }

            SvgTextContent.FILL.name,
            SvgTextContent.FILL_OPACITY.name,
            SvgTextContent.STROKE.name,
            SvgTextContent.STROKE_OPACITY.name,
            SvgTextContent.STROKE_WIDTH.name -> super.setAttribute(target, name, value)

            else -> super.setAttribute(target, name, value)
        }
    }

    fun revalidatePositionAttributes(svgTextAnchor: String?, target: Text) {
        TODO()
        //val width = target.boundsInLocal.width
        //when (svgTextAnchor) {
        //    SvgConstants.SVG_TEXT_ANCHOR_END -> {
        //        target.translateX = -width
        //        target.textAlignment = TextAlignment.RIGHT
        //    }
        //    SvgConstants.SVG_TEXT_ANCHOR_MIDDLE -> {
        //        target.translateX = -width / 2
        //        target.textAlignment = TextAlignment.CENTER
        //    }
        //    else -> {
        //        target.translateX = 0.0
        //        target.textAlignment = TextAlignment.LEFT
        //    }
        //}
    }
}