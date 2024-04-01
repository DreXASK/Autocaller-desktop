package core.presentation.utils

fun String?.useNonBreakingSpace() = this.orEmpty()
    .replace(
        Constants.REGULAR_SPACE_CHARACTER,
        Constants.NON_BREAKABLE_SPACE_UNICODE
    )