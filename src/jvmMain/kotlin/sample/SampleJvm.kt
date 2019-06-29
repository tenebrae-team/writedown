package sample

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*
import kotlinx.css.*
import kotlinx.css.Display.*

private const val fontsSource = "https://fonts.googleapis.com/css?family=Lato|PT+Serif&display=swap"

@Suppress("unused")
fun Application.main() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                    link(rel = "stylesheet", href = fontsSource, type = "text/css")
                }
                body("wrapper") {
                    div("wrapper") {
                        id = "js-response"
                        div("loading-wrapper wrapper") {
                            div("js-loading") {
                                +"Loading"
                            }
                        }
                    }
                    script(src = "/main.bundle.js") {
                    }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                rule("html, .wrapper") {
                    width = 100.pct
                    height = 100.pct
                    fontSize = 13.px
                }
                rule(".loading-wrapper") {
                    display = flex
                    justifyContent = JustifyContent.center
                    alignItems = Align.center
                }
                rule("*") {
                    fontFamily = "'Lato', sans-serif"
                    margin = "0"
                    padding = "0"
                }
                rule(".js-loading") {
                    color = Color("#008899")
                }
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}