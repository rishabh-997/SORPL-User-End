package com.rishabh.sorpluserend.Help.MVP;

import com.rishabh.sorpluserend.Utilities.ClientAPI;
import com.rishabh.sorpluserend.Utilities.Utils;

public class HelpPresenter implements HelpContract.presenter
{
    HelpContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public HelpPresenter(HelpContract.view mvpview) {
        this.mvpview = mvpview;
    }

}
