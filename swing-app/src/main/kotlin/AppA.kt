/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package svgMapperDemo

import DemoUtil
import jetbrains.datalore.vis.svgMapper.demo.DemoModelA

fun main() {
    DemoUtil.show(DemoModelA.createModel(), "SwingSkia DemoA")
}
