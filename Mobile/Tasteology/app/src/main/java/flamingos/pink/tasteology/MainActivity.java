package flamingos.pink.tasteology;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView popularIV, cuisineIV, categoriesIV, recommendedIV;
    ImageView homeIV, searchIV, accountIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popularIV = (ImageView) findViewById(R.id.iv_popular);
        cuisineIV = (ImageView) findViewById(R.id.iv_cuisine);
        categoriesIV = (ImageView) findViewById(R.id.iv_categories);
        recommendedIV = (ImageView) findViewById(R.id.iv_recommended);

        homeIV = (ImageView) findViewById(R.id.iv_home);
        searchIV = (ImageView) findViewById(R.id.iv_search);
        accountIV = (ImageView) findViewById(R.id.iv_account);

        popularIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : get all popular recipes and show display on search activity
            }
        });
        cuisineIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : get all cuisine recipes and show display on search activity
            }
        });
        categoriesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : get all categories and show display on search activity
            }
        });
        recommendedIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : get all recommended recipes and show display on search activity
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
