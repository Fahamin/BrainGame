package fff.phot.seeker.braingame.allactivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.File;

import fff.phot.seeker.braingame.gameclass.TicTacToe;
import fff.phot.seeker.braingame.gameclass.TicTacToeAIEasy;
import fff.phot.seeker.braingame.gameclass.TicTacToeAIHard;
import fff.phot.seeker.braingame.gameclass.TicTacToeAINormal;
import fff.phot.seeker.braingame.R;

public class GameScreenActivity extends AppCompatActivity {
    protected TextView playerTV, cpuTV, difficultyTV;

    protected Button btnTL, btnTM, btnTR;
    protected Button btnML, btnMM, btnMR;
    protected Button btnBL, btnBM, btnBR;
    protected TextView txtPlayerScore, txtCPUScore;
    protected TicTacToe game;
    protected TextView lblStatus;
    private InterstitialAd mInterstitialAd;
    Button lv;


    private int aiOption = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        aiOption = bundle.getInt("option");


        btnTL = (Button) findViewById(R.id.btnTL);
        btnTM = (Button) findViewById(R.id.btnTM);
        btnTR = (Button) findViewById(R.id.btnTR);
        btnML = (Button) findViewById(R.id.btnML);
        btnMM = (Button) findViewById(R.id.btnMM);
        btnMR = (Button) findViewById(R.id.btnMR);
        btnBL = (Button) findViewById(R.id.btnBL);
        btnBM = (Button) findViewById(R.id.btnBM);
        btnBR = (Button) findViewById(R.id.btnBR);

        txtPlayerScore = (TextView) findViewById(R.id.txtPlayerScore);
        txtCPUScore = (TextView) findViewById(R.id.txtCPUScore);

        playerTV = (TextView) findViewById(R.id.player_TV);
        cpuTV = (TextView) findViewById(R.id.cpu_TV);
        difficultyTV = (TextView) findViewById(R.id.difficulty_TV);


        MobileAds.initialize(getApplicationContext(), getString(R.string.banner_home_footer_1));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        if (aiOption == 1) {
            game = new TicTacToeAIEasy();

            difficultyTV.setText("LEVEL: EASY");

        } else if (aiOption == 2) {
            game = new TicTacToeAINormal();

            difficultyTV.setText("LEVEL: NORMAL");

        } else if (aiOption == 3) { //aiOption==3
            game = new TicTacToeAIHard();
            difficultyTV.setText("LEVEL: HARD");

        }

        lv = findViewById(R.id.levelChangeid);

        lv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mInterstitialAd = new InterstitialAd(GameScreenActivity.this);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen1));
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        showInterstitial();
                    }
                });

                Intent i = new Intent(GameScreenActivity.this, DifficultySelectionScreenActivity.class);
                startActivity(i);
                //startActivity(new Intent(GameScreenActivity.this,DifficultySelectionScreenActivity.class));
            }
        });
        game.setController(this);
        game.resetBoard();


        btnTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(0, 0);
            }
        });


        btnTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(1, 0);
            }
        });


        btnTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(2, 0);
            }
        });


        btnML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(0, 1);
            }
        });


        btnMM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(1, 1);
            }
        });


        btnMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(2, 1);
            }
        });


        btnBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(0, 2);
            }
        });


        btnBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(1, 2);
            }
        });


        btnBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.genericBtnClick(2, 2);
            }
        });


    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void createOverlay(String strMessage) {

    }

    public void resetButtons() {

        String blank = "";
        //lblStatus.setText(blank);
        btnTL.setText(blank);
        btnTM.setText(blank);
        btnTR.setText(blank);
        btnML.setText(blank);
        btnMM.setText(blank);
        btnMR.setText(blank);
        btnBL.setText(blank);
        btnBM.setText(blank);
        btnBR.setText(blank);
    }

    public void setBtnText(int i, int j, String text) {
        if (i == 0 && j == 0) btnTL.setText(text);
        else if (i == 1 && j == 0) btnTM.setText(text);
        else if (i == 2 && j == 0) btnTR.setText(text);
        else if (i == 0 && j == 1) btnML.setText(text);
        else if (i == 1 && j == 1) btnMM.setText(text);
        else if (i == 2 && j == 1) btnMR.setText(text);
        else if (i == 0 && j == 2) btnBL.setText(text);
        else if (i == 1 && j == 2) btnBM.setText(text);
        else if (i == 2 && j == 2) btnBR.setText(text);
    }

    public void setLblText(String text) {//lblStatus.setText(text);}
    }

    public void setBanner(String text) {

    }

    public void setTxtPlayerScore(int score) {
        txtPlayerScore.setText(String.valueOf(score));
    }

    public void setTxtCPUScore(int score) {
        txtCPUScore.setText(String.valueOf(score));
    }

    public void setAiOption(int x) {
        aiOption = x;
    }

    public int getAiOption() {
        return aiOption;
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

*/


}
