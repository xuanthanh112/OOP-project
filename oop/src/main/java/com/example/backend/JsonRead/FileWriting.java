package com.example.backend.JsonRead;

import com.example.backend.CrawlData.Blog.NftBlogCrawl;
import com.example.backend.CrawlData.NftFloor.Binance.BinanceAPICall1D;
import com.example.backend.CrawlData.NftFloor.Binance.BinanceAPICall7D;
import com.example.backend.CrawlData.NftFloor.NiftyGateway.NiftyGateway1DScraper;
import com.example.backend.CrawlData.NftFloor.NiftyGateway.NiftyGateway7DScraper;
import com.example.backend.CrawlData.NftFloor.OpenSea.OpenSea1DScraper;
import com.example.backend.CrawlData.NftFloor.OpenSea.OpenSea7DScraper;
import com.example.backend.CrawlData.NftFloor.Rarible.RaribleAPICall1D;
import com.example.backend.CrawlData.NftFloor.Rarible.RaribleAPICall7D;
import com.example.backend.CrawlData.Twitter.TwitterScraper;

public class FileWriting {
    public void WriteData(){
        NiftyGateway1DScraper NiftyGateway1DScraper = new NiftyGateway1DScraper();
        NiftyGateway7DScraper NiftyGateway7DScraper = new NiftyGateway7DScraper();
        BinanceAPICall1D Binance1D = new BinanceAPICall1D();
        BinanceAPICall7D Binance7D = new BinanceAPICall7D();
        OpenSea1DScraper Op1D = new OpenSea1DScraper();
        OpenSea7DScraper Op7D = new OpenSea7DScraper();
        RaribleAPICall1D Rarible1D = new RaribleAPICall1D();
        RaribleAPICall7D Rarible7D = new RaribleAPICall7D();
        NiftyGateway7DScraper.call();
        NiftyGateway1DScraper.call();
        Binance1D.call();
        Binance7D.call();
        Op1D.call();
        Op7D.call();
        Rarible1D.call();
        Rarible7D.call();
        TwitterScraper tweet = new TwitterScraper();
        //tweet.call();

        NftBlogCrawl blog = new NftBlogCrawl();
        blog.call();
    }

}
