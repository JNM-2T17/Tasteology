package flamingos.pink.tasteology;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    Spinner searchType;
    EditText searchKey;
    Button btnSearch;
    RecyclerView rvRecipes;

    ImageView homeIV, searchIV, accountIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        homeIV = (ImageView) findViewById(R.id.iv_home);
        searchIV = (ImageView) findViewById(R.id.iv_search);
        accountIV = (ImageView) findViewById(R.id.iv_account);

        searchType = (Spinner) findViewById(R.id.search_type);
        searchKey = (EditText) findViewById(R.id.et_search_key);
        btnSearch = (Button) findViewById(R.id.button_search);
        rvRecipes = (RecyclerView) findViewById(R.id.recycler_recipe);

        //TODO : preset the choices for the spinner
        //TODO : adapter for recycle view

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: update result based on search
            }
        });

        homeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(i);
                finish();
            }
        });
        accountIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : create intent to go to account activity
            }
        });

    }

}
