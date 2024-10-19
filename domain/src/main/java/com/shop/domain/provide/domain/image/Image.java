package com.shop.domain.provide.domain.image;

import com.shop.domain.provide.domain.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    private Date getDate;
    private String imageName;

    public Image(Item item) {
        this.imageName = item.getItemName();
        this.getDate = new Date();
    }
}
