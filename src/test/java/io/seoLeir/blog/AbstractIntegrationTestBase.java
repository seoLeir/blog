package io.seoLeir.blog;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


@Sql(value = "classpath:sql/insert-data.sql")
@SpringBootTest(classes = SocialMediaApplication.class)
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class AbstractIntegrationTestBase extends AbstractContainerHolder {

}
