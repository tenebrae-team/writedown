package sample

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.defaultValue
import react.dom.div
import styled.*
import kotlin.browser.document
import kotlin.browser.localStorage

interface TextFieldProps : RProps {
    var placeholder: String
}

interface TextFieldState : RState {
}


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
external val DecoupledEditor: RClass<CKEditorProps>

interface EditorPageProps : RProps {
    var title: String
    var author: String
    var text: String
}

class EditorPage : RComponent<EditorPageProps, RState>() {
    init {
        StyledComponents.injectGlobal {
            rule("#editor-toolbar") {
                rule(".ck-toolbar") {
                    flexDirection = FlexDirection.column
                    padding(0.px)
                    borderLeftStyle = BorderStyle.none
                }
                rule(".ck-button") {
                    margin(0.px)
                    padding(5.px)
                }
            }

            rule("#editor-content") {
                rule(".ck-content") {
                    +ArticleStyles.text
                    margin(0.px)
                    padding(0.px)
                    borderStyle = BorderStyle.none
                    boxShadow = BoxShadows.none
                }
            }
        }
    }

    override fun RBuilder.render() {
        leftSided {
            div {
                attrs.id = "editor-toolbar"
            }
            css {
                display = Display.flex
                height = 100.pct
                alignItems = Align.center
            }
        }
        centered {
            spacer()

            styledInput(InputType.text) {
                attrs.placeholder = "Title.."
                attrs.defaultValue = props.title
                attrs.onChangeFunction = {
                    val value = it.target.unsafeCast<INPUT>().value
                    localStorage.setItem("draftTitle", value)
                }
                css {
                    +ArticleStyles.title
                    +ComponentStyles.unborderedTextField
                    width = 100.pct
                    padding(5.px, 0.px)
                    display = Display.block
                }
            }
            styledInput(InputType.text) {
                attrs.placeholder = "Your name.."
                attrs.defaultValue = props.author
                attrs.onChangeFunction = {
                    val value = it.target.unsafeCast<INPUT>().value
                    localStorage.setItem("draftAuthor", value)
                }
                css {
                    +ArticleStyles.author
                    +ComponentStyles.unborderedTextField
                    width = 100.pct
                    padding(3.px, 0.px)
                    display = Display.block
                }
            }
            div {
                attrs.id = "editor-content"
                CKEditor {
                    attrs.editor = DecoupledEditor
                    attrs.data = props.text
                    attrs.config = js("""{
                        placeholder: 'Your text..',
                        toolbar: [ 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ]
                    }""")
                    attrs.onInit = {
                        val toolbar = it.ui.view.toolbar.element
                        document.getElementById("editor-toolbar")!!.appendChild(toolbar)
                    }
                    attrs.onChange = fun(_, editor) {
                        localStorage.setItem("draftText", editor.getData())
                    }
                }
            }
        }
    }
}