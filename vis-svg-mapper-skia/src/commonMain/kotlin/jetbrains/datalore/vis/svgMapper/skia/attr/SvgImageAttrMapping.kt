/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.vis.svgMapper.skia.attr

import jetbrains.datalore.vis.svg.SvgImageElement
import jetbrains.datalore.vis.svgMapper.skia.drawing.Image
import java.util.*

internal object SvgImageAttrMapping : SvgAttrMapping<Image>() {
    override fun setAttribute(target: Image, name: String, value: Any?) {
        when (name) {
            SvgImageElement.X.name -> target.x = value?.asFloat ?: 0.0f
            SvgImageElement.Y.name -> target.y = value?.asFloat ?: 0.0f
            SvgImageElement.WIDTH.name -> target.width = value?.asFloat ?: 0.0f
            SvgImageElement.HEIGHT.name -> target.height = value?.asFloat ?: 0.0f
            SvgImageElement.PRESERVE_ASPECT_RATIO.name -> target.preserveRatio = asBoolean(value)
            SvgImageElement.HREF.name -> setHrefDataUrl(target, value as String)
            else -> super.setAttribute(target, name, value)
        }
    }

    fun setHrefDataUrl(target: Image, dataUrl: String): ByteArray {
        val base64Str = dataUrl.split(",")[1]
        val imageBytes = Base64.getDecoder().decode(base64Str)
        updateTargetImage(target, imageBytes)
        return imageBytes
    }

    private fun updateTargetImage(target: Image, imageBytes: ByteArray?) {
        target.img = org.jetbrains.skia.Image.makeFromEncoded(imageBytes)
    }
}