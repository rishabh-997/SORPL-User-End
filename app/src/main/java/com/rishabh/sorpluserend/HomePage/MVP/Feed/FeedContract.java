package com.rishabh.sorpluserend.HomePage.MVP.Feed;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;

public class FeedContract
{
    interface view{

        void showList(AffiliationResponse body);

        void toast(String message);
    }
    interface presenter{

        void getFeeds();
    }
}
