package dev.wirespec.sample.ui.screens.petdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.wirespec.sample.R
import dev.wirespec.sample.models.PetListItemInfo
import dev.wirespec.sample.ui.components.DetailProperty
import dev.wirespec.sample.ui.components.MultiLineText
import dev.wirespec.sample.ui.theme.AppTheme

@Composable
fun PetDetailStatsUI(
    pet: PetListItemInfo,
    modifier: Modifier = Modifier,
    onAdoptClick:  () -> Unit
) {
    Text(
        pet.name,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        DetailProperty(R.string.gender, if (pet.gender == "m") stringResource(R.string.male) else stringResource(R.string.female))
        DetailProperty(R.string.born, pet.birthdate)
        DetailProperty(R.string.color, pet.color)
    }
    MultiLineText(
        pet.description,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Center, modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Button(
            modifier = Modifier.widthIn(max = 200.dp),
            colors = AppTheme.getButtonColors(),
            elevation = ButtonDefaults.elevation(5.dp),
            onClick = onAdoptClick
        ) {
            Text(
                text = stringResource(R.string.adopt) + " " + pet.name,
                modifier = modifier.padding(start = 10.dp, top = 7.dp, end = 10.dp, bottom = 7.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}