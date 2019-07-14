package com.rishabh.sorpluserend.HomePage.MVP.Cart;

import com.rishabh.sorpluserend.HomePage.Model.CartResponse;

public class CartContract
{
    interface view
    {

        void showCart(CartResponse body);

        void toast(String message);

        void delete(int pos, String message);
    }
    interface presenter
    {

        void getCart(String mobile, String company);

        void deleteCart(String pid, int pos);

        void send(String accessToken);
    }
}
