package com.rishabh.sorpluserend.MyDetails.MVP;

import com.rishabh.sorpluserend.MyDetails.Model.ResponseClient;

public class DetailContract
{
    interface  view
    {

        void showToast(String message);

        void showData(ResponseClient body);
    }
    interface presenter
    {

        void search(String query);

        void update(String name, String mobile, String phone, String email, String pan, String billto, String shipto, String gst, String bankname, String ifsc, String isc, String bankphone, String accno, String msmenumber, String preferred, String regtype);
    }
}
