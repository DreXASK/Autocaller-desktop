package serverScreen.domain.repository.callProcessSettings

sealed interface CallProcessSettingsTypes {
    sealed interface Parameter: CallProcessSettingsTypes {
        interface Get: Parameter
        interface Send: Parameter
    }
//    sealed interface Response: CallTaskTypes {
//        interface Get: Response
//    }
}