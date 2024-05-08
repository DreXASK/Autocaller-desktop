package core.domain.repository.callTasks

sealed interface CallTaskTypes {
    sealed interface Parameter: CallTaskTypes {
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