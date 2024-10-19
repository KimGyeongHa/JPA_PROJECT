package com.shop.domain.provide.domain.job.file;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileData implements Serializable {
    private String ID;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;

    @Builder
    public FileData(String id
                    ,String lastName
                    , String firstName
                    , String position
                    , int birthYear
                    , int debutYear
                    ) {
        this.ID = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.birthYear = birthYear;
        this.debutYear = debutYear;
    }
}
