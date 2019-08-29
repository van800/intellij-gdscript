package gdscript.colors

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey

object GdColors {

    val KEYWORD: TextAttributesKey = DefaultLanguageHighlighterColors.KEYWORD
    val NUMBER: TextAttributesKey = DefaultLanguageHighlighterColors.NUMBER
    val STRING: TextAttributesKey = DefaultLanguageHighlighterColors.STRING
    val LINE_COMMENT: TextAttributesKey = DefaultLanguageHighlighterColors.LINE_COMMENT
    val BLOCK_COMMENT: TextAttributesKey = DefaultLanguageHighlighterColors.BLOCK_COMMENT
    val PARAMETER: TextAttributesKey = DefaultLanguageHighlighterColors.PARAMETER

    fun all() = listOf(KEYWORD, NUMBER, STRING, LINE_COMMENT, BLOCK_COMMENT, PARAMETER)

}