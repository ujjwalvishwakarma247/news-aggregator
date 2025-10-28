package com.example.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Mycontroller {


    
    private final RssfeedService service;

    

    // @GetMapping("/news")
    // public List<NewsItems> news(){
    //    List<NewsItems> news = RssfeedService.fetchNewFromRss("https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
    //     return news;
    // }

    public Mycontroller(RssfeedService service) {
        this.service = service;
    }

    @GetMapping("/")
        public String home() {
         return "home";
        }



    @GetMapping("/news")
    public String news(Model m){
       List<NewsItems> news = service.fetchNewFromRss("https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
       m.addAttribute("link", news);
        return "newsall";
    }
}
