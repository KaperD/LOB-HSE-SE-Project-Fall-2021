package ru.hse.sport.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Value("\${postgres.jdbcUrl}")
    private lateinit var jdbcUrl: String
    @Value("\${postgres.username}")
    private lateinit var username: String
    @Value("\${postgres.password}")
    private lateinit var password: String
    @Value("\${postgres.initialization.fail.timeout}")
    private var initializationFailTimeout: Long = 10000

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = jdbcUrl
        config.username = username
        config.password = password
        config.initializationFailTimeout = initializationFailTimeout
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        return HikariDataSource(config)
    }

    @Bean
    fun namedParameterJdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}
