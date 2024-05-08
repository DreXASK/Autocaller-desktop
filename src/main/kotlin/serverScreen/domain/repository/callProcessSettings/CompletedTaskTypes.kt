package serverScreen.domain.repository.completedTasks

sealed interface CompletedCallTaskTypes {
    sealed interface Parameter: CompletedCallTaskTypes {
        interface Get: Parameter
    }
//    sealed interface Response: CallTaskTypes {
//        interface Get: Response
//    }
}