package sample

import kotlinx.css.Position
import kotlinx.css.em
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.html.DIV
import kotlinx.html.id
import react.*
import styled.*


fun RBuilder.centered(content: StyledDOMBuilder<DIV>.() -> Unit) {
    styledDiv {
        css {
            maxWidth = 800.px
            width = 100.pct
            margin = "0 auto"
        }
        content()
    }
}

fun RBuilder.leftSided(content: StyledDOMBuilder<DIV>.() -> Unit) {
    styledDiv {
        css {
            position = Position.fixed
            left = 0.em
            top = 0.em
        }
        attrs.id = "left"
        content()
    }
}