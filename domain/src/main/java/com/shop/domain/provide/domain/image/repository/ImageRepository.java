package com.shop.domain.provide.domain.image.repository;

import com.shop.domain.provide.domain.image.Image;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public void insertImages(Image image){
        em.persist(image);
    }







}
