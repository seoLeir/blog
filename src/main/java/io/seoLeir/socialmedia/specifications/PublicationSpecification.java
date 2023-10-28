package io.seoLeir.socialmedia.specifications;


import io.seoLeir.socialmedia.entity.Publication;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PublicationSpecification{
    public static Specification<Publication> publicationOrderBySpecification(String text, UUID id){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (id != null){
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), id));
            }
            Predicate innerPredicate = null;
            if(text != null){
                Predicate textSearchPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("text")), "%" + text.toLowerCase() + "%");
                Predicate tittleSearchPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("tittle")), "%" + text.toLowerCase() + "%");
                innerPredicate = criteriaBuilder.or(textSearchPredicate, tittleSearchPredicate);
            }
            if (innerPredicate != null)
                predicates.add(innerPredicate);

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
