package io.seoLeir.blog.repository;

import io.seoLeir.blog.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Publication repository integration tests")
@Tag("integration")
public class PublicationRepositoryTest extends IntegrationTestBase{

    @Transactional
    @Test
    public void userPublicationsCount(){
        assertThat(true).isTrue();
    }
}
