package sample

import kotlinx.css.Color
import kotlinx.css.Color.Companion
import kotlinx.css.Display.flex
import kotlinx.css.properties.boxShadow
import kotlinx.css.px
import org.w3c.dom.HTMLMarqueeElement
import react.*
import react.dom.*
import styled.css
import styled.styledDiv
import kotlin.browser.*

fun RBuilder.hello(name: String) {
    h1 {
        +"Hello, $name"
    }

    styledDiv {
        css {
            display = flex
            boxShadow(Color.red, 20.px, -20.px)
        }
        +"<i>shandowed</i>"
    }
    +"Hi! from Kotlin.JS ${hello()}"
}

class SampleComponent : RComponent<SampleProps, RState>() {
    override fun RBuilder.render() {
        h2 {
            +"sempl ${props.content}"
        }
    }
}

interface SampleProps : RProps {
    var content: String
}

fun RBuilder.sample(content: String) {
    child(SampleComponent::class) {
        attrs.content = content
    }
}

fun main() {
    render(document.getElementById("js-response")) {
        hello("np")
        sample("slava")
    }
}