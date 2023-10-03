package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID> {


    @Query("select p from Publication p where p.user.id = :userUuid")
    List<Publication> getAllByUser(UUID userUuid);

//    @Modifying(flushAutomatically = true)
//    @Query("update Publication p set " +
//            "p.files = :#{publication.files}, " +
//            "p.header = :#{publication.header}, " +
//            "p.text = :#{publication.text} " +
//            "where p.id = :#{publication.id}")
//    int update(@Param("publication") Publication publication);

    @Modifying
    void deleteById(@Nullable UUID id);
}
