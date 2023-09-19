package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationOptionalParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationOptionalParameterRepository extends JpaRepository<PublicationOptionalParameter, Long> {
}
