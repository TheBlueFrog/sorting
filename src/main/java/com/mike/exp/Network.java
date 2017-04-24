package com.mike.exp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by mike on 3/24/2017.
 */
public class Network {

    private Route route;
    public Route getRoute () {
        return route;
    }

    // 344b8437-ff96-4092-934b-85b6baf95eb6
    public Network (String milkRun) {
        String ip = "104.154.66.16";
//        String ip = "amilkrun.com";

        String url = String.format("http://%s/milkrun-api/get/%s", ip, milkRun);
        String charset = "UTF-8";
        String param1 = "value1";
        String param2 = "value2";
// ...

        try {

            String query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));

            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            Gson gson = new Gson();

            //Route route = gson.fromJson(response, Route.class);
            route = new Gson().fromJson(
                    new InputStreamReader(response, "UTF-8"),
                    new TypeToken<Route>(){}.getType());

        } catch (Exception e) {

        }
    }
}
