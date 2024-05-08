package core.domain.repository.callTasks

sealed interface CallTasksTypes {
    sealed interface Parameter: CallTasksTypes {
        interface Get: Parameter
        interface Remove: Parameter
        interface Send: Parameter
    }
    sealed interface Response: CallTasksTypes {
        interface Get: Response
        interface Remove: Response
        interface Send: Response
    }
}