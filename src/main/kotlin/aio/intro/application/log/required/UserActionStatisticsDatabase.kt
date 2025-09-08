package aio.intro.application.log.required

interface UserActionStatisticsDatabase {
    fun addRow(
        id: String,
        serviceName: String,
        visitorCount: Long,
        emailButtonClickCount: Long
    )
}