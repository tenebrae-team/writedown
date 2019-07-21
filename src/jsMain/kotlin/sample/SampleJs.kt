package sample

import kotlinx.css.Display.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.*
import kotlinx.css.Color.Companion.red
import react.*
import react.dom.*
import react.router.dom.*
import styled.*
import kotlin.browser.*


external interface CKEditorProps : RProps {
    var editor: dynamic
}

@JsModule("@ckeditor/ckeditor5-react")
external val CKEditor: RClass<CKEditorProps>

@JsModule("@ckeditor/ckeditor5-build-classic")
external val CKEditorClassic: dynamic


fun RBuilder.hello(name: String) {
    h1 {
        +"Hello, $name"
    }

    styledH1 {
        css {
            backgroundColor = red
        }
        +"HENONONONNOL"
    }

    styledDiv {
        css {
            display = flex
            boxShadow(red, 20.px, (-20).px)
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
        CKEditor {
            childList.addAll(Children.toArray(props.children))
            attrs.editor = CKEditorClassic
        }
    }
}

class RootComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            switch {
                route("/", SampleComponent::class, exact = true)
                route("/a", exact = true) {
                    h1 {
                        +"EXACC"
                    }
                }
                route("/b", exact = false) {
                    h1 {
                        +"DISEXACC"
                    }
                }
                route("/gitara//", strict = true) {
                    navLink("/gitare//") {
                        +"HAHAHAHAHAHAHA"
                    }
                }
                route("/gitare//", strict = false) {
                    navLink("/gitara//") {
                        +"11111"
                    }
                }
            }
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

        child(RootComponent::class) { /* */ }
    }
}