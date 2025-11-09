package com.example.news;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
@Component
public  class RssfeedService {

    @Autowired
    private Repositorynews repo;

    // 🔁 This method will run every 10 minutes automatically
    @Scheduled(fixedRate = 600000) // 10 minutes = 600000 milliseconds
    public List<NewsItems> fetchNewFromRss(String rssUrl){
        List<NewsItems> newsList = new ArrayList<>();
        try {
            URL feedUrl =new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            for (SyndEntry entry : feed.getEntries()) {

                String category = detectCategory(entry.getTitle(), entry.getDescription().toString());
                 
                String contents =entry.getDescription().getValue()!=null? entry.getDescription().getValue() : " anal ";

                NewsItems items= new NewsItems(entry.getTitle() , entry.getLink() , entry.getAuthor() , category , contents);

                items.setGuid(entry.getUri());

                if (repo.findByLink(entry.getLink()) == null) {
                   repo.save(items); 
                }
                
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return ((Collection<NewsItems>) repo.findAll()).stream().toList();
    }


    public String detectCategory(String title, String description) {
    String content = (title + " " + description).toLowerCase();

    if (content.contains("cricket") || content.contains("football") || content.contains("score"))
        return "Sports";
    if (content.contains("politic") || content.contains("election") || content.contains("minister"))
        return "Politics";
    if (content.contains("movie") || content.contains("actor") || content.contains("film"))
        return "Entertainment";
    if (content.contains("tech") || content.contains("software") || content.contains("gadget"))
        return "Technology";

    return "Other";
}

    
}
