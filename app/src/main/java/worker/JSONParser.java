package worker;

/**
 * Created by QtoR2 on 05/02/2016.
 */

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jArray = null;
    static String json = "";

    // constructor
    public JSONParser() {
    }

    // NOUVELLE METHODE - Utilise HttpURLConnection

    public JSONObject getJSONObjectFromUrl(final String url) {
        try {
            URL monURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) monURL.openConnection();

            //TO DO : Mettre le compte IUT de Jérémie
            String infosCompte = "username:password";
            String encoded = Base64.encodeToString(infosCompte.getBytes(), Base64.DEFAULT);

            conn.setRequestProperty("Authorization", "basic " + encoded);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "utf-8");

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            is = conn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Create a BufferedReader to parse through the inputStream.
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            // Declare a string builder to help with the parsing.
            StringBuilder sb = new StringBuilder();
            // Declare a string to store the JSON object data in string form.
            String line = null;

            // Build the string until null.
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // Close the input stream.
            is.close();
            // Convert the string builder data to an actual string.
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // Try to parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Return the JSON Object.
        return jObj;
    }

    public JSONObject makeHttpRequest(String url, List<String> params, List<String> values) {
        URL monURL;
        String query = null;
        HttpURLConnection conn = null;
        try {
            for (int i = 0; i < params.size(); i++) {
                query = String.format(params.get(i) + "=%s", URLEncoder.encode(values.get(i), "UTF-8"));
            }
            monURL = new URL(url + "?" + query);
            Log.i("url", monURL.toString());
            conn = (HttpURLConnection) monURL.openConnection();

            String infosCompte = "bretonq:wv5qR8qv";
            String encoded = Base64.encodeToString(infosCompte.getBytes(), Base64.DEFAULT);

            conn.setRequestProperty("Authorization", "basic " + encoded);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "utf-8");

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            is = conn.getInputStream();
            Log.d("content", is.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }

    public JSONArray getJSONArrayFromUrl(final String url) {
        // Making HTTP request
        try {
            URL monURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) monURL.openConnection();

            //TO DO : Mettre le compte IUT de Jérémie
            String infosCompte = "username:password";
            String encoded = Base64.encodeToString(infosCompte.getBytes(), Base64.DEFAULT);

            conn.setRequestProperty("Authorization", "basic " + encoded);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "utf-8");

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            is = conn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Create a BufferedReader to parse through the inputStream.
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            // Declare a string builder to help with the parsing.
            StringBuilder sb = new StringBuilder();
            // Declare a string to store the JSON object data in string form.
            String line = null;

            // Build the string until null.
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // Close the input stream.
            is.close();
            // Convert the string builder data to an actual string.
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // Try to parse the string to a JSON object
        try {
            jArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Return the JSON Object.
        return jArray;
    }

    public JSONArray makeHttpRequestArray(String url, List<String> params,
                                          List<String> values) {

        URL monURL;
        String query = null;
        HttpURLConnection conn = null;
        try {
            if (params != null) {
                query = "";
                for (int i = 0; i < params.size(); i++) {
                    if (!query.equals("")) {
                        query += "&";
                    }
                    query += String.format(params.get(i) + "=%s", URLEncoder.encode(values.get(i), "UTF-8"));
                }
            }

            if (query != null) {
                monURL = new URL(url + "?" + query);
            } else {
                monURL = new URL(url);
            }
            Log.i("url", monURL.toString());
            conn = (HttpURLConnection) monURL.openConnection();

            String infosCompte = "bretonq:wv5qR8qv";
            String encoded = Base64.encodeToString(infosCompte.getBytes(), Base64.DEFAULT);

            conn.setRequestProperty("Authorization", "basic " + encoded);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "utf-8");

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            is = conn.getInputStream();
            Log.d("content", is.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                Log.d("content", line);
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jArray = new JSONArray(json);

        } catch (JSONException e) {
            try {
                JSONObject jObj = new JSONObject(json);
            } catch (Exception f) {
                Log.e("JSON Parser", "Error parsing data " + f.toString());
                return jArray = null;
            }
            return jArray = null;

        }

        // return JSON String
        return jArray;
    }


}
