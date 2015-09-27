package com.morristaedt.mirror.modules;

import android.os.AsyncTask;
import android.util.Log;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.util.List;

/**
 * Created by alex on 21/09/15.
 */
public class NewsModule {
    public interface NewsListener {
        void onNewNews(String headline);
    }

    public static void getNewsHeadline(final NewsListener newsListener) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                newsListener.onNewNews(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RSSReader rssReader = new RSSReader();
                String url = "http://www.nu.nl/rss/Algemeen";
                try {
                    RSSFeed feed = rssReader.load(url);
                    List<RSSItem> items = feed.getItems();
                    String stringOfItems = items.get(0).getTitle();
                    for(int i = 1; i < items.size();    i++)    {
                        stringOfItems += " | "+items.get(i).getTitle();
                    }
                    System.out.println(stringOfItems);
                    return stringOfItems;
                } catch (RSSReaderException e) {
                    Log.e("NewsModule", "Error parsing RSS");
                    return null;
                }
            }
        }.execute();
    }
}
