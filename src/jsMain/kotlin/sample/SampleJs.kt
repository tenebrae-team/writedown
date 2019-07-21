package sample

import react.*
import react.dom.*
import react.router.dom.*
import kotlin.browser.*


class RootComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            switch {
                route("/", exact = true) {
                    child(EditorPage::class) {
                        attrs.text = "hihihihihhuhuhuhuhuhu"
                    }
                }
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

fun main() {
    render(document.getElementById("js-response")) {
        child(RootComponent::class) { }
    }
}