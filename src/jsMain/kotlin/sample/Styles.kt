package sample

import kotlinx.css.BorderStyle
import kotlinx.css.FontWeight
import kotlinx.css.Outline
import kotlinx.css.properties.LineHeight
import kotlinx.css.px
import styled.StyleSheet


object ArticleStyles : StyleSheet("article") {
    val title by css {
        fontSize = 25.px
        fontWeight = FontWeight.bold
    }
    val author by css {
        fontSize = 15.px
    }
    val text by css {
        fontFamily = "'PT Serif', serif"
        fontSize = 17.px
        lineHeight = LineHeight("1.5")
    }
}

object ComponentStyles : StyleSheet("component") {
    val unborderedTextField by css {
        outline = Outline.none
        borderStyle = BorderStyle.none
    }
}