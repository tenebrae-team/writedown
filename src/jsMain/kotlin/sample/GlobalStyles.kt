package sample

import kotlinx.css.*
import kotlinx.css.properties.BoxShadows
import kotlinx.css.properties.lh

fun globalStyles(builder: CSSBuilder) {
    builder.apply {
        rule(".ck.ck-toolbar") {
            flexDirection = FlexDirection.column
            padding = "0"
            borderLeftStyle = BorderStyle.none
        }
        rule(".ck.ck-content") {
            fontFamily = "'PT Serif', serif"
            fontSize = 17.px
            lineHeight = 1.5.em.lh
        }
        rule(".ck.ck-content.ck-editor__editable.ck-focused") {
            border = "1px solid transparent"
            boxShadow = BoxShadows.none
        }
        rule(".ck.ck-button") {
            margin = "0"
            padding = "5px"
        }
    }
}