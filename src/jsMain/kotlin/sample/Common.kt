package sample

import kotlinx.css.CSSBuilder
import kotlin.browser.document


fun addStyle(content: CSSBuilder.() -> Unit) {
    val css = CSSBuilder().apply(content)
    val styleNode = document.createElement("style")
    styleNode.innerHTML = css.toString()
    document.head!!.appendChild(styleNode)
}