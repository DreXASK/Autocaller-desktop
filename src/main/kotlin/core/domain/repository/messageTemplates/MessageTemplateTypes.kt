package core.domain.repository.messageTemplates

sealed interface MessageTemplateTypes {
    sealed interface Parameter: MessageTemplateTypes {
        interface Get: Parameter
        interface Remove: Parameter
        interface Send: Parameter
    }
//    sealed interface Response: CallTaskTypes {
//        interface Get: Response
//        interface Remove: Response
//        interface Send: Response
//    }
}