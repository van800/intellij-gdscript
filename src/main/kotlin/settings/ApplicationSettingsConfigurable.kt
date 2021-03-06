package settings

import api.VersionedClassesService
import com.intellij.openapi.components.service
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.VerticalFlowLayout
import java.awt.BorderLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel

class ApplicationSettingsConfigurable : SearchableConfigurable {

    private val settings = service<ApplicationSettings>()
    private val lspCheckbox = initLspCheckbox()
    private val versionList = initVersionList()

    override fun getDisplayName() = "GDScript"

    override fun isModified(): Boolean {
        val lspChanged = settings.lspEnabled != lspCheckbox.isSelected
        val versionChanged = settings.apiVersion != versionList.selectedItem
        return lspChanged || versionChanged
    }

    override fun getId() = "ApplicationSettingsConfigurable"

    override fun apply() {
        settings.lspEnabled = lspCheckbox.isSelected
        settings.apiVersion = versionList.selectedItem as String
    }

    override fun createComponent(): JComponent? {
        val panel = JPanel(VerticalFlowLayout())
        panel.add(LabeledComponent.create(versionList, "API version:").also { it.labelLocation = BorderLayout.WEST })
        panel.add(lspCheckbox)
        return panel
    }

    private fun initLspCheckbox(): JCheckBox =
        JCheckBox("LSP features (experimental)", settings.lspEnabled)

    private fun initVersionList(): ComboBox<String> {
        val classesService = service<VersionedClassesService>()
        val availableVersions = classesService.versions.map { it.name }.toTypedArray()
        val combo = ComboBox(availableVersions)
        combo.selectedItem = settings.apiVersion
        return combo
    }

}