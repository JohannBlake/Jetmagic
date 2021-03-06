package dev.wirespec.sample.ui.screens.petdetails

import androidx.compose.foundation.ScrollState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.wirespec.jetmagic.composables.IImageManager
import dev.wirespec.jetmagic.composables.ImageManager
import dev.wirespec.jetmagic.composables.crm
import dev.wirespec.jetmagic.models.ComposableInstance
import dev.wirespec.jetmagic.navigation.navman
import dev.wirespec.sample.da.Repository
import dev.wirespec.sample.models.PetListItemInfo
import kotlinx.coroutines.launch

class PetDetailsViewModel: ViewModel(), IImageManager {

    var screenScrollState: ScrollState = ScrollState(0)
    var petInfo: PetListItemInfo? = null

    private val imageMan = ImageManager()

    override val imageManager: ImageManager
        get() = imageMan

    fun processDeepLink(composableInstance: ComposableInstance) {
        val deepLink = navman.getDeepLinkForComposableInstance(composableInstance = composableInstance)

        if (deepLink != null) {
            val queryParamName = deepLink.queryKeyValues["name"]

            if (queryParamName.isNullOrEmpty()) {
                return
            }

            viewModelScope.launch {
                petInfo = Repository.getPetByName(queryParamName)
                crm.notifyComposableInstanceOfUpdate(composableInstance = composableInstance)
            }

            // Since we don't support any further deep links beyond the details screen,
            // clear any additional deep links that might still be on the stack.
            navman.clearDeepLinks()
        }
    }
}