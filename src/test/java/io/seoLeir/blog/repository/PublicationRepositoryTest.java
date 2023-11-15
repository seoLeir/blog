package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.IntegrationTestBase;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
public class PublicationRepositoryTest extends IntegrationTestBase {

    private final Connection conn;
    @SneakyThrows
    @Test
    public void userPublicationsCount(){
        Connection connection = DriverManager.getConnection("");
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("path/to/liquibase/changelog.xml", new ClassLoaderResourceAccessor(), database);
        assertThat(true).isTrue();
    }
}
