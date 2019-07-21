package sample

import kotlinx.css.BorderStyle
import kotlinx.css.FlexDirection
import kotlinx.css.px
import react.*
import kotlin.browser.document


external interface CKEditorProps : RProps {
    var editor: dynamic
    var data: String
    var config: dynamic
    var onInit: (dynamic) -> Unit
}

@JsModule("@ckeditor/ckeditor5-react")
external val CKEditor: RClass<CKEditorProps>

@JsModule("@ckeditor/ckeditor5-build-decoupled-document")
external val DecoupledEditor: dynamic


interface EditorPageProps : RProps {
    var text: String
}

class EditorPage : RComponent<EditorPageProps, RState>() {
    override fun RBuilder.render() {
        addStyle {
            rule(".ck.ck-toolbar") {
                flexDirection = FlexDirection.column
                padding = "0"
                borderLeftStyle = BorderStyle.none
            }
            rule(".ck.ck-content") {
                fontFamily = "'PT Serif', serif"
                fontSize = 15.px
            }
            rule(".ck.ck-button") {
                margin = "0"
                padding = "5px"
            }
        }
        leftSided {
        }
        centered {
            CKEditor {
                attrs.editor = DecoupledEditor
                attrs.data = props.text
                attrs.config = js("""{
                    toolbar: [ 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ]
                }""")
                attrs.onInit = {
                    val toolbar = it.ui.view.toolbar.element
                    document.getElementById("left")!!.appendChild(toolbar)
                }
            }
        }
    }
}