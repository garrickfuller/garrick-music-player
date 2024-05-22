package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*
 * Represents song data stored in database
 */
@Document
@Data // this is lombok for boilerplate getter() setter() ToString etc
public class Song {

    @Id //mark as id so spring knows to create a unique id for every song in database
    private String id;

    private String filename;

    private String title;

    private String artist;


}
