package flamingos.pink.tasteology;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewRecipeActivity extends AppCompatActivity {

    ImageView recipePhotoIV, likeIV;
    TextView tvCategory, tvName, tvLikes;
    RecyclerView rvIngredients;

    ImageView homeIV, searchIV, accountIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);


        homeIV = (ImageView) findViewById(R.id.iv_home);
        searchIV = (ImageView) findViewById(R.id.iv_search);
        accountIV = (ImageView) findViewById(R.id.iv_account);

        recipePhotoIV = (ImageView) findViewById(R.id.recipe_photo);
        likeIV = (ImageView) findViewById(R.id.like);

        rvIngredients = (RecyclerView) findViewById(R.id.recycler_ingredient);

        tvCategory = (TextView) findViewById(R.id.recipe_category);
        tvName =  (TextView) findViewById(R.id.recipe_title);
        tvLikes = (TextView) findViewById(R.id.recipe_likes);

        //TODO : show data of recipe and
        // TODO: set adapter and show all ingredients in recyecle view

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
