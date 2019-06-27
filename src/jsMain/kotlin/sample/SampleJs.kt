package sample

import kotlinx.css.Color
import kotlinx.css.Color.Companion
import kotlinx.css.Display.flex
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.BoxShadows
import kotlinx.css.properties.boxShadow
import kotlinx.css.px
import react.*
import react.dom.*
import styled.css
import styled.styledDiv
import kotlin.browser.*

actual class Sample {
    actual fun checkMe() = 12
}

actual object Platform {
    actual val name: String = "JS"
}

fun RBuilder.hello(name: String) {
    h1 {
        +"Hello, $name"
    }

    styledDiv {
        css {
            display = flex
            boxShadow(Color.red, 20.px, (-20).px)
        }
        +"shandowed"
    }
    +"Hi! from Kotlin.JS ${hello()}, check me value: ${Sample().checkMe()}"
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