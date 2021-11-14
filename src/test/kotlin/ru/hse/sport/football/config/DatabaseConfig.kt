package ru.hse.sport.football.config

import io.zonky.test.db.postgres.embedded.LiquibasePreparer
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    companion object {
        var db = EmbeddedPostgresExtension.preparedDatabase(
            LiquibasePreparer.forClasspathLocation("liquibase/changelog.xml")
        )
    }

    @Bean
    fun dataSource(): DataSource {
        db.beforeAll(null)
        return db.testDatabase
    }

    @Bean
    fun namedParameterJdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}
