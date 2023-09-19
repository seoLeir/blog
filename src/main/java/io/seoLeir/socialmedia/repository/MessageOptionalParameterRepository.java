package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.MessageOptionalParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageOptionalParameterRepository extends JpaRepository<MessageOptionalParameter, Long> {
}
