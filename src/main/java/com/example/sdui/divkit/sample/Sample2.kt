package com.example.sdui.divkit.sample

import divkit.dsl.*
import divkit.dsl.scope.DivScope
import org.springframework.stereotype.Component

@Component
class Sample2 : Sample {

	companion object {
		@JvmField
		val sample = divan {
			data(
				logId = "sample2",
				states = singleRoot(
					div = column(
						items = listOf(
							header("header") + textProps(margins = edgeInsets(top = 10)),
							body("text1", "text2") + textProps(margins = edgeInsets(all = 8)),
						)
					)
				)
			)
		}

		fun DivScope.header(title: String) = text(
			text = title,
			fontWeight = bold,
			fontSize = 24,
			lineHeight = 28
		)

		fun DivScope.body(text1: String, text2: String) = text(
			text = text1 + " " + text2,
			fontSize = 18
		)
	}

	override fun getSample() = sample

	override fun getId() = 2
}
