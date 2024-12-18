package junit.com.svenruppert.junit5.basics.c08.example.webapp.docker;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeleoidTest {




  @Test
  void test001() {
//    GenericContainer<?> selenoid = new GenericContainer<>("aerokube/selenoid:latest-release")
//        .withExposedPorts(4444)
//        .withEnv("OVERRIDE_VIDEO_OUTPUT_DIR", "/tmp/video")
//        .withEnv("TZ", "UTC");
//    selenoid.start();

//    BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
//        .withCapabilities(new ChromeOptions())
//        .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"));
//    chrome.start();
  }

  @Test
  void test002() {
    DockerImageName POSTGRES_TEST_IMAGE = DockerImageName.parse("postgres:9.6.12");
    try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRES_TEST_IMAGE)) {
      postgres.start();


    }

//    protected ResultSet performQuery(JdbcDatabaseContainer<?> container, String sql) throws SQLException {
//      DataSource ds = getDataSource(container);
//      Statement statement = ds.getConnection().createStatement();
//      statement.execute(sql);
//      ResultSet resultSet = statement.getResultSet();
//
//      resultSet.next();
//      return resultSet;
//    }
//    protected DataSource getDataSource(JdbcDatabaseContainer<?> container) {
//      HikariConfig hikariConfig = new HikariConfig();
//      hikariConfig.setJdbcUrl(container.getJdbcUrl());
//      hikariConfig.setUsername(container.getUsername());
//      hikariConfig.setPassword(container.getPassword());
//      hikariConfig.setDriverClassName(container.getDriverClassName());
//      return new HikariDataSource(hikariConfig);
//    }
  }
}
