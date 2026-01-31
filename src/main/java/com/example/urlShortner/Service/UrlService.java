package com.example.urlShortner.Service;

import com.example.urlShortner.Common.ShorteningUtil;
import com.example.urlShortner.DTO.FullUrl;
import com.example.urlShortner.DTO.ShortUrl;
import com.example.urlShortner.Models.UrlEntity;
import com.example.urlShortner.Repository.UrlRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UrlService {
    Logger logger= LoggerFactory.getLogger(UrlService.class);

    private final UrlRepo urlRepository;

    @Autowired
    public UrlService(UrlRepo urlRepository){
        this.urlRepository = urlRepository;
    }


    private UrlEntity get(Long id){
        logger.info("Fetching Url from database for Id {}", id);
        if(urlRepository.findById(id).isPresent())
            return urlRepository.findById(id).get();
        else
            return null;
    }

    /*
        This Method : Converts shortenedURL to Id
        and With the Help of Id it will return FullURL from Database.
        @param shortenedURL
        @return FullUrl
     */
    public FullUrl getFullURL(String shortenedUrl){
        Long id = ShorteningUtil.strTOId(shortenedUrl);
        System.out.println("Corresponding id :" + id);
        return new FullUrl(Objects.requireNonNull(get(id)).getFullUrl());
    }

    /*
          Check if the FullURL already exists in the DB or not
          @param FullUrl
          @return ShortUrl
     */
    public ShortUrl getShortURL(FullUrl fullUrl){
        System.out.println("Checking if the "+fullUrl.getFullUrl() + " exists in the Database.");
        List<UrlEntity> savedUrls = checkFullUrlExistsInDB(fullUrl);
        UrlEntity savedUrl;

        if(savedUrls.isEmpty()){  //FullURL doesn't Exists in DB -> save it
            logger.info("url: {} saving in Database.",fullUrl.getFullUrl());
            savedUrl=this.save(fullUrl);
        }else{
            savedUrl=savedUrls.get(0);
            logger.info("url: {} already exists in the database. skipping insert", savedUrl);
        }
        String shortURLText=ShorteningUtil.idToStr(savedUrl.getId());
        logger.info("Converted Base 10 {} to Base 62 string {}", savedUrl.getFullUrl(), shortURLText);
        return new ShortUrl(shortURLText);
    }

    private List<UrlEntity> checkFullUrlExistsInDB(FullUrl fullUrl){
        return urlRepository.findByFullUrl(fullUrl.getFullUrl());
        //urlRepository.findByFullUrl(fullUrl.getFullUrl()) : returns List<UrlEntity>
    }

    private UrlEntity save(FullUrl fullUrl){
        return urlRepository.save(new UrlEntity(fullUrl.getFullUrl()));
    }

    private UrlEntity save(FullUrl fullUrl,ShortUrl shortUrl){
        return urlRepository.save(new UrlEntity(fullUrl.getFullUrl(),shortUrl.getShortUrl()));
    }

}
