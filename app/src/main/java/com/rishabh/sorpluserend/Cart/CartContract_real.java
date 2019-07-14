package com.rishabh.sorpluserend.Cart;

import com.rishabh.sorpluserend.HomePage.Model.CartResponse;

public class CartContract_real
{
    interface view
    {
        void showCart(CartResponse body);

        void toast(String message);

        void delete(int pos, String message);

        void exithere();
    }
    interface presenter
    {

        void getCart(String mobile, String company);

        void deleteCart(String pid, int pos);

        void send(String accessToken);
    }
}
