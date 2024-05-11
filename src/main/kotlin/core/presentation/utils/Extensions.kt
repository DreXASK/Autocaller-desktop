package core.presentation.utils

fun String?.useNonBreakingSpace() = this
    .orEmpty()
    .replace(
        Constants.REGULAR_SPACE_CHARACTER,
        Constants.NON_BREAKABLE_SPACE_UNICODE
    )

fun String?.useNonBreakingHyphen() = this.orEmpty()
    .replace(
        Constants.REGULAR_HYPHEN_CHARACTER,
        Constants.NON_BREAKABLE_HYPHEN_UNICODE
    )

fun String.applyPhoneVisualTransformation(): String {
    val builder = StringBuilder()

    builder.append(this[0])
    builder.append(" (")
    builder.append(this.substring(1..3))
    builder.append(") ")
    builder.append(this.substring(4..6))
    builder.append(" ")
    builder.append(this.substring(7..8))
    builder.append(" ")
    builder.append(this.substring(9..10))

    return builder.toString()
}