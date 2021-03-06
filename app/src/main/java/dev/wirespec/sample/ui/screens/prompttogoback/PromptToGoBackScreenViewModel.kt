package dev.wirespec.sample.ui.screens.prompttogoback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.wirespec.jetmagic.navigation.NavigateBackOptions
import dev.wirespec.jetmagic.navigation.NavigationManagerHelper
import dev.wirespec.jetmagic.navigation.navman

class PromptToGoBackScreenViewModel : ViewModel(), NavigationManagerHelper {

    private val _onDisplayDialog = MutableLiveData(false)
    val onDisplayDialog: LiveData<Boolean> = _onDisplayDialog

    fun onDialogResponse(confirmed: Boolean) {
        _onDisplayDialog.value = false

        if (confirmed) {
            navman.goBackImmediately()
        }
    }

    override fun onNavigateBack(): NavigateBackOptions? {
        _onDisplayDialog.value = true
        return NavigateBackOptions.Cancel
    }
}