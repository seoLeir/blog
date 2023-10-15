package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID>, PagingAndSortingRepository<Message, UUID> {

    @Query("select m from Message m where m.userFrom.username = :username order by m.sentDateTime desc ")
    Page<Message> getUserAllDialoguesUsernameOrderBySentDateTime(@Param("username") String username, Pageable pageable);
}
