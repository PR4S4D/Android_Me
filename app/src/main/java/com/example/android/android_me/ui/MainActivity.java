package com.example.android.android_me.ui;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private Button button;
    private Boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.android_me);

        if(findViewById(R.id.android_me_linear_layout) != null){
            twoPane = true;
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageIds(AndroidImageAssets.getHeads());

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.header_container, headFragment).commit();
            fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();
            fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
        }else{
            twoPane = false;
        }

    }

    @Override
    public void onImageSelected(int position) {

        int bodyPartNumber = position / 12;
        int listIndex = position - 12*bodyPartNumber;

        if(twoPane){
            switch (bodyPartNumber) {
                case 0:
                    BodyPartFragment headFragment = new BodyPartFragment();
                    headFragment.setImageIds(AndroidImageAssets.getHeads());
                    headFragment.setImageIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.header_container,headFragment).commit();
                    break;
                case 1:
                    BodyPartFragment bodyFragment = new BodyPartFragment();
                    bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyFragment.setImageIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container,bodyFragment).commit();
                    break;
                case 2:
                    BodyPartFragment legFragment = new BodyPartFragment();
                    legFragment.setImageIds(AndroidImageAssets.getLegs());
                    legFragment.setImageIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container,legFragment).commit();
                    break;
            }
        }else{

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
            }


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(MainActivity.this,AndroidMeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("headIndex",headIndex);
                    bundle.putInt("bodyIndex",bodyIndex);
                    bundle.putInt("legIndex",legIndex);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }




    }
}
