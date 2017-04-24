package com.mike.exp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mike on 3/24/2017.
 */
public class CartOffer {

    private final String id;
    private int quantity;
    private String item;
    private String supplier;
    private String consumer;

    public CartOffer(String id) {
        this.id = id;
    }

    // given a route stop that is a Drop extract the
    // CartOffers and set the Consumer

    public static Collection<? extends CartOffer> fromDrop(Route.Stop stop) {
        List<CartOffer> cartOffers = new ArrayList<>();
        for (Route.Item item : stop.getItems()) {
            CartOffer cartOffer = new CartOffer(item.cartOfferId);
            cartOffer.setConsumer(stop.getUsername());
            cartOffer.setQuantity(item.getQuantity());
            cartOffer.setItem(item.getItem());
            cartOffers.add(cartOffer);
        }
        return cartOffers;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getId() {
        return id;
    }

    public void setSupplier(Route.Stop stop) {
        this.supplier = stop.getUsername();
    }

    public String getItem() {
        return item;
    }

    public String getSupplier() {
        return supplier;
    }
}
