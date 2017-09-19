package unicornheight.gitlistsearchwithvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //the URL having the json data
    private static final String JSON_URL = "https://api.github.com/search/users?q=language:java+location:lagos"; //"https://simplifiedcoding.net/demos/view-flipper/heroes.php"; // "https://api.github.com/search/users?q=language:java+location:lagos"

    //listview object
    ListView listView;

    //the git list where we will store all the hero objects after parsing json
    List<GitUser> gitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initializing listview and git list
        listView = (ListView) findViewById(R.id.listView);
        gitList = new ArrayList<>();

        //this method will fetch and parse the data
        loadHeroList();
    }

    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named gituser inside the object
                            //so here we are getting that json array
                            JSONArray gitArray = obj.getJSONArray("items");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < gitArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject gitObject = gitArray.getJSONObject(i);

                                //creating a gituser object and giving them the values from json object
                                GitUser git = new GitUser(gitObject.getString("login"),gitObject.getString("avatar_url"), gitObject.getString("url"));

                                //adding the gitusers to list
                                gitList.add(git);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(gitList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
