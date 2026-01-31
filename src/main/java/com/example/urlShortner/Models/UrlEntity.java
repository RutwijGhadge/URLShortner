package com.example.urlShortner.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullURL")
    private String fullUrl;

    @Column(name = "shortURL")
    private String shortUrl;

    public UrlEntity(String fullUrl) {
        this.fullUrl=fullUrl;
    }

    public UrlEntity(String fullUrl,String shortUrl) {
        this.fullUrl=fullUrl;
        this.shortUrl=shortUrl;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id=" + id +
                ", longUrl='" + fullUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
