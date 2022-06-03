package com.example.profilemanager;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RatingParser extends AsyncTask<String, Void, String> {
    final String API = "https://codeforces.com/api/user.rating?handle=";
    private HttpURLConnection con;

    public ArrayList<Contest> contests;


    @Override
    protected String doInBackground(String ...strings) {
        try {
            getRatedList(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public final static RatingParser RATING_PARSER = new RatingParser();

    private RatingParser() {

    }

    public static RatingParser getInstance() {
        return RATING_PARSER;
    }

    public void getRatedList(String username) throws IOException, JSONException {
        try {

            URL url = new URL(API + username);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
//            int status = con.getResponseCode();
//            if (status > 300) {
//                return new ArrayList<Contest>();
//            }
            StringBuilder response = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while((line = br.readLine()) != null) response.append(line);
            JSONObject userData = new JSONObject(response.toString());
            JSONArray ratings = new JSONArray(userData.getJSONArray("result"));
            ArrayList<Contest> contests = new ArrayList<>();
            for(int i = 0 ; i <ratings.length() ; i++) {
                JSONObject con = ratings.getJSONObject(i);
                contests.add(new Contest(con.getInt("oldRating"), con.getInt("newRating"), con.getString("contestName")));
            }
            this.contests = contests;
        }
        catch(IOException e) {

        }
    }
}
