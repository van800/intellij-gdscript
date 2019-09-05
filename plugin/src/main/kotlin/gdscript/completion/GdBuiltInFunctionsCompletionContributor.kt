package gdscript.completion

import Library
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType.BASIC
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.PlatformIcons.*
import gdscript.completion.utilities.PrioritizedLookupCompletionProvider


class GdBuiltInFunctionsCompletionContributor : CompletionContributor() {

    init {
        val library = Library.load()
        for (clazz in library.classes) {
            extend(BASIC, psiElement(), PrioritizedLookupCompletionProvider(listOf(clazz.toNameLookup())))
            extend(BASIC, psiElement(), PrioritizedLookupCompletionProvider(clazz.fields.map { it.toLookup() }))
            extend(BASIC, psiElement(), PrioritizedLookupCompletionProvider(clazz.methods.map { it.toLookup() }))
        }
    }

    private fun Library.Class.toNameLookup() =
        LookupElementBuilder.create(name)
            .withIcon(CLASS_ICON)

    private fun Library.Class.Field.toLookup() =
        LookupElementBuilder.create(name)
            .withIcon(VARIABLE_ICON)
            .withTypeText(type)

    private fun Library.Class.Method.toLookup() =
        LookupElementBuilder.create(name)
            .withIcon(METHOD_ICON)
            .withTailText("()")
            .withTypeText(type)
}
