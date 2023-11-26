package io.seoLeir.blog.specifications;


import io.seoLeir.blog.entity.Publication;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;


public class PublicationSpecification{

    @Getter
    private Specification<Publication> publicationSpecification;

    public PublicationSpecification(Map<String, Object> predicateMap) {
        publicationSpecification = Specification.where(null);
        predicateMap.forEach((key, value) ->
                publicationSpecification = switch (key) {
                    case "id" -> idPredicate(value);
                    case "text" -> textLike(value);
                    case "title" -> titleLike(value);
                    default -> publicationSpecification;
                });
    }

    public static Specification<Publication> idPredicate(Object id){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("id"), id);
    }

    public static Specification<Publication> textLike(Object stringToFindInText){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("text")), "%" + String.valueOf(stringToFindInText).toLowerCase() + "%");
    }

    public static Specification<Publication> titleLike(Object stringToFindInTitle){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("tittle")), "%" + String.valueOf(stringToFindInTitle).toLowerCase() + "%");
    }
}
