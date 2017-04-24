package com.mike.exp;

/**
 * Created by mike on 3/24/2017.
 */
public class Route {
        public final String name;
        public final String timestamp;
        public final Stop stops[];

        public Route(String name, String timestamp, Stop[] stops){
            this.name = name;
            this.timestamp = timestamp;
            this.stops = stops;
        }

        public static final class Item {
            public int quantity;
            public String cartOfferId;
            public String item;

            public Item () {
            }

            public int getQuantity () {
                return quantity;
            }
            public String getCartOfferId () {
                return cartOfferId;
            }
            public String getItem () {
                return item;
            }
        }

        public static final class Stop {
            static private int nextSerialNumber = 1;

            private String username;
            public String name;
            public String companyName;
            public String type;
            private String address;
            public double longitude;
            public double latitude;
            public Item[] items;

            public final int serialNumber = nextSerialNumber++;

            public Stop () {

            }

            //        public Stop(String name, String type, String address, double latitude, double longitude, Item[] items){
//            this.name = name;
//            this.type = type;
//            this.address = address;
//            this.items = items;
//            this.latitude = latitude;
//            this.longitude = longitude;
//        }
//
//        public String getUsername() {
//            return username;
//        }
            public String getUsername() {
                return username;
            }
            public String getName() {
                return name;
            }
            public String getCompanyName() { return companyName; }
            public Item[] getItems() {
                return items;
            }
            public int getSerialNumber() { return serialNumber; }
            public String getType() {
                return type;
            }
            public String getAddress() {
                return address;
            }

            public boolean isUnrouteable() {
                // both will be zero if the stop is unroutable, e.g.
                // has no known location
                return (Math.abs(latitude) + Math.abs(longitude)) < 0.1;
            }

        }
}
