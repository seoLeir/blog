package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
