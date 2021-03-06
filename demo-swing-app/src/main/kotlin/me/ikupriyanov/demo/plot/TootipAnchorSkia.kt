package me.ikupriyanov.demo.plot

import jetbrains.datalore.plotDemo.model.plotConfig.TooltipAnchor
import jetbrains.datalore.vis.swing.skia.PlotViewerWindowSkia

fun main() {
    with(TooltipAnchor()) {
        PlotViewerWindowSkia(
            "Tooltip Anchor",
            null,
            plotSpecList().first()
        ).open()
    }
}
