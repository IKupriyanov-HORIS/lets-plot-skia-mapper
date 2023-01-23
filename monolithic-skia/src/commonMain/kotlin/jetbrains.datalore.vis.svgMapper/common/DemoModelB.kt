package jetbrains.datalore.vis.svgMapper.common

import jetbrains.datalore.base.values.Color
import jetbrains.datalore.vis.svg.*

object DemoModelB {
    fun createModel(): SvgSvgElement {
        val svgRoot = SvgSvgElement(400.0, 200.0)

        val ellipse = SvgEllipseElement(200.0, 80.0, 170.0, 50.0)

        // style `stroke` is not working with JavaFX Scene mapper if stroke opacity
        // is later defined via element's attribute
        // because there is no separate `stroke-opacity` attribute.
        // So the color will be just reset to black by SVG -> Scene mapper.
        // ellipse.getAttribute("style").set("stroke:#006600;")
        // ellipse.addClass("ellipse-yellow")

        // This will reset stroke color to black with JavaFX Scene mapper.
        // ellipse.strokeOpacity().set(0.6)

        ellipse.fillColor().set(Color.YELLOW)
        ellipse.strokeColor().set(Color.parseHex("#006600"))
        ellipse.strokeWidth().set(2.0)
        ellipse.strokeOpacity().set(0.6)

        val text = SvgTextElement(20.0, 20.0, "Example Text")

        val pathBuilder = SvgPathDataBuilder(false)
        pathBuilder.moveTo(150.0, 175.0, true)
            .verticalLineTo(-100.0)
            .ellipticalArc(100.0, 100.0, 0.0, false, false, -100.0, 100.0)
            .closePath()

        val path = SvgPathElement(pathBuilder.build())
        path.fill().set(SvgColors.RED)
        path.stroke().set(SvgColors.create(0, 0, 255))
        path.getAttribute("stroke-width").set("5")

        val ellipse2 = SvgEllipseElement(250.0, 65.0, 40.0, 85.0)
        ellipse2.fill().set(SvgColors.GREEN)

        val rect = SvgRectElement(180.0, 50.0, 80.0, 50.0)
        rect.fillColor().set(Color(255, 0, 0, 100))

        val image = SvgImageElement(256.0, 64.0, 128.0, 64.0)
        image.href().set(SvgUtils.pngDataURI(SampleImageData.MINDUKA_PRESENT_BLUE_PACK))

        val bitmap = SvgImageElementEx.Bitmap(3, 3, SampleImageData.argb3x3())
        val imageEx = SvgImageElementEx(180.0, 110.0, 110.0, 80.0, bitmap)
//        imageEx.preserveAspectRatio().set("true")

        svgRoot.children().add(ellipse)
        svgRoot.children().add(rect)
        svgRoot.children().add(ellipse2)
        svgRoot.children().add(text)
        svgRoot.children().add(path)
        svgRoot.children().add(image)
        svgRoot.children().add(imageEx)

        return svgRoot
    }


    private fun addCircle(svgRoot: SvgSvgElement, x: Int, y: Int) {
        val circle = SvgCircleElement(x.toDouble(), y.toDouble(), 10.0)
        circle.fillColor().set(Color.BLACK)

        svgRoot.children().add(circle)
    }

    internal object SampleImageData {
        const val MINDUKA_PRESENT_BLUE_PACK =
            "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"
        const val CODE_BEAUTIFY =
            "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"


        fun argb3x3(): IntArray {
            //  R  |  G  |  B    alpha = 0.5
            //  R  |  G  |  B    alpha = 1
            // .5  |  1  |  .5   <-- gray, alpha

            return intArrayOf(
                SvgUtils.toARGB(Color.RED, 0.5), SvgUtils.toARGB(Color.GREEN, 0.5), SvgUtils.toARGB(Color.BLUE, 0.5),
                SvgUtils.toARGB(Color.RED), SvgUtils.toARGB(Color.GREEN), SvgUtils.toARGB(Color.BLUE),
                SvgUtils.toARGB(Color.BLACK, 0.5), SvgUtils.toARGB(Color.BLACK, 1.0), SvgUtils.toARGB(Color.BLACK, 0.5)
            )
        }
    }

    class CssRes : SvgCssResource {
        override fun css(): String {
            return ".ellipse-yellow { \n" +
                    "fill: yellow;\n" +
                    "}"
        }
    }
}