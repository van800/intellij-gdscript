package gdscript.colors

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import gdscript.GdLanguage
import gdscript.highlight.GdHighlighterFactory
import gdscript.GdIcons


class GdColorSettingsPage : com.intellij.openapi.options.colors.ColorSettingsPage {

    override fun getAdditionalHighlightingTagToDescriptorMap() =
        emptyMap<String,TextAttributesKey>()

    override fun getIcon() =
        GdIcons.FILE_ICON

    override fun getHighlighter() =
        GdHighlighterFactory().getSyntaxHighlighter(null, null)

    override fun getDemoText() =
        GdColorSettingsPage::class.java.getResource("/demo.gd").readText()

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> =
        GdColor.values()
        .map { it.textAttributesKey }
        .map { attr -> AttributesDescriptor(humanize(attr), attr) }
        .toTypedArray()

    override fun getColorDescriptors() =
        emptyArray<ColorDescriptor>()

    override fun getDisplayName() =
        GdLanguage.displayName

    private fun humanize(attribute: TextAttributesKey) =
        attribute.externalName
        .trim()
        .split("_")
        .map { it.toLowerCase() }
        .filter { it != "default" }
        .joinToString(" ")
        .capitalize()

}