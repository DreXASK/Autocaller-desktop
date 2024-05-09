package core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.domain.models.MessageTemplatePlaceholders
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.*


@Composable
fun MessageTemplatePlaceholdersUi(
    templateFieldText: MutableState<String>,
    templatePlaceholders: MutableState<MessageTemplatePlaceholders>,
    cardModifier: Modifier = Modifier,
    readMode: Boolean = false
) {
    Card(modifier = cardModifier) {
        Column {
            Text(
                text = "Заглушки",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Divider()

            val toggleButtonModifier = Modifier
                .padding(5.dp)
                .width(IntrinsicSize.Max)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                item {
                    ToggleButton(
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isSurnamePlaceholderUsed = !templatePlaceholders.value.isSurnamePlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isSurnamePlaceholderUsed)
                                    templateFieldText.value.plus(SURNAME_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(SURNAME_PLACE_HOLDER, "")
                        },
                        modifier = toggleButtonModifier,
                        isToggled = templatePlaceholders.value.isSurnamePlaceholderUsed,
                        text = "Фамилия"
                    )
                }
                item {
                    ToggleButton(
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isNamePlaceholderUsed = !templatePlaceholders.value.isNamePlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isNamePlaceholderUsed)
                                    templateFieldText.value.plus(NAME_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(NAME_PLACE_HOLDER, "")
                        },
                        isToggled = templatePlaceholders.value.isNamePlaceholderUsed,
                        modifier = toggleButtonModifier,
                        text = "Имя"
                    )
                }
                item {
                    ToggleButton(
                        isToggled = templatePlaceholders.value.isPatronymicPlaceholderUsed,
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isPatronymicPlaceholderUsed = !templatePlaceholders.value.isPatronymicPlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isPatronymicPlaceholderUsed)
                                    templateFieldText.value.plus(PATRONYMIC_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(PATRONYMIC_PLACE_HOLDER, "")
                        },
                        modifier = toggleButtonModifier,
                        text = "Отчество"
                    )
                }
                item {
                    ToggleButton(
                        isToggled = templatePlaceholders.value.isPhoneNumberPlaceholderUsed,
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isPhoneNumberPlaceholderUsed = !templatePlaceholders.value.isPhoneNumberPlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isPhoneNumberPlaceholderUsed)
                                    templateFieldText.value.plus(PHONE_NUMBER_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(PHONE_NUMBER_PLACE_HOLDER, "")
                        },
                        modifier = toggleButtonModifier,
                        text = "Номер телефона"
                    )
                }
                item {
                    ToggleButton(
                        isToggled = templatePlaceholders.value.isSexPlaceholderUsed,
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isSexPlaceholderUsed = !templatePlaceholders.value.isSexPlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isSexPlaceholderUsed)
                                    templateFieldText.value.plus(SEX_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(SEX_PLACE_HOLDER, "")
                        },
                        modifier = toggleButtonModifier,
                        text = "Пол"
                    )
                }
                item {
                    ToggleButton(
                        isToggled = templatePlaceholders.value.isAgePlaceholderUsed,
                        onClick = {
                            if (readMode)
                                return@ToggleButton

                            templatePlaceholders.value = templatePlaceholders.value.copy(
                                isAgePlaceholderUsed = !templatePlaceholders.value.isAgePlaceholderUsed
                            )
                            templateFieldText.value =
                                if (templatePlaceholders.value.isAgePlaceholderUsed)
                                    templateFieldText.value.plus(AGE_PLACE_HOLDER)
                                else
                                    templateFieldText.value.replace(AGE_PLACE_HOLDER, "")
                        },
                        modifier = toggleButtonModifier,
                        text = "Возраст"
                    )
                }
            }
        }
    }
}
