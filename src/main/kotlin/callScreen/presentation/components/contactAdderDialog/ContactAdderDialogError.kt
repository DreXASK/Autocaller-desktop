package callScreen.presentation.components.contactAdderDialog

import core.domain.utils.Error

enum class ContactAdderDialogError: Error {
    PHONE_NUMBER_IS_NULL,
    PHONE_NUMBER_IS_NOT_11_DIGITS_LENGTH
}