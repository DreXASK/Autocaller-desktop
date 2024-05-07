package callScreen.domain.repository.contacts

sealed interface ContactsDto {
    sealed interface Parameter: ContactsDto {
        interface Get: Parameter
        interface Send: Parameter
    }
    sealed interface Response: ContactsDto {
        interface Get: Response
        interface Send: Response
    }
}