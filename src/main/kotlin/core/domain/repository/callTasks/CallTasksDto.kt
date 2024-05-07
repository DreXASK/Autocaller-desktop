package core.domain.repository.callTasks

sealed interface CallTasksDto {
    sealed interface Parameter: CallTasksDto {
        interface Get: Parameter
        interface Send: Parameter
    }
    sealed interface Response: CallTasksDto {
        interface Get: Response
        interface Send: Response
    }
}