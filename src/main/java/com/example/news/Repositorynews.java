package com.example.news;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface Repositorynews extends CrudRepository<NewsItems,Integer>{

    NewsItems findByLink(String link);
    
} 
