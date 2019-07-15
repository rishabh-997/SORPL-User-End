package com.rishabh.sorpluserend.Affiliation.MVP;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;

public class AffiliationContract
{
    interface view{

        void showList(AffiliationResponse body);

        void toast(String message);
    }
    interface presenter{

        void getList();
    }
}
