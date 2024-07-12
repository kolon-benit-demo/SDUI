package com.example.sdui.divkit

import divkit.dsl.*

object Sample {

	@JvmField
	val sample = divan {
		data(
			logId = "my-layout-id",
			states = singleRoot(
				div = column(
					width = wrapContentSize(),
					height = wrapContentSize(),
					margins = edgeInsets(left = 10, right = 10, top = 5, bottom = 5),
					items = listOf(
						text("Hello, world!", fontSize = 18),
						text("Hello, world!", fontSize = 18)
					)
				)
			)
		)
	}

	@JvmStatic
	fun getSample() = sample
}
