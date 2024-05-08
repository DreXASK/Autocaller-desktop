package callScreen.domain.repository.contacts

sealed interface ContactsTypes {
    sealed interface Parameter: ContactsTypes {
        interface Get: Parameter
        interface Send: Parameter
    }
//    sealed interface Response: ContactsTypes {
//        interface Get: Response
//        interface Send: Response
//    }
}