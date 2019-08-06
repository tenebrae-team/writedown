package sample

import react.*
import kotlin.browser.document
import kotlin.browser.localStorage

external interface CKEditorProps : RProps {
    var editor: dynamic
    var data: String
    var config: dynamic
    var onInit: (dynamic) -> Unit
    var onChange: (dynamic, dynamic) -> Unit
}

@JsModule("@ckeditor/ckeditor5-react")
external val CKEditor: RClass<CKEditorProps>

@JsModule("@ckeditor/ckeditor5-build-decoupled-document")
external val DecoupledEditor: dynamic

external fun require(name: String): dynamic

val styles = require("ckeditor.css")

interface EditorPageProps : RProps {
    var text: String
}

class EditorPage : RComponent<EditorPageProps, RState>() {
    override fun RBuilder.render() {
        leftSided {
        }
        centered {
            CKEditor {
                console.log(styles)
                attrs.editor = DecoupledEditor
                attrs.data = props.text
                attrs.config = js("""{
                    toolbar: [ 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ]
                }""")
                attrs.onInit = {
                    val toolbar = it.ui.view.toolbar.element
                    document.getElementById("left")!!.appendChild(toolbar)
                }
                attrs.onChange = fun(_, editor) {
                    localStorage.setItem("draftText", editor.getData())
                }
            }
        }
    }
}