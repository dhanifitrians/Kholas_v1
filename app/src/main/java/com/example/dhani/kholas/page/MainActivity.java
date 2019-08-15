package com.example.dhani.kholas.page;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.adapter.SuratAdapter;
import com.example.dhani.kholas.model.BaseSurat;
import com.example.dhani.kholas.model.GalleryItem;
import com.example.dhani.kholas.utils.CheckForSDCard;
import com.example.dhani.kholas.utils.DownloadTask;
import com.example.dhani.kholas.utils.GalleryUtils;
import com.example.dhani.kholas.utils.Utils;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tampiltarget;

    private final static String JSON_LIST_SURAT = "data-surat.json";
    private final static int pageSurah = 604;
    //Deceleration of list of  GalleryItems
    public List<GalleryItem> galleryItems;
    //Read storage permission request code
    private static final int RC_READ_STORAGE = 5;

    File apkStorage = null;

    private SuratAdapter suratAdapter;
    private ListView listView;

    Button closeButton;
    AlertDialog.Builder builder;
    ProgressBar progress;

    int mPosisi;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progress);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //check for read storage permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //Get images
            galleryItems = GalleryUtils.getImages(this);
            // add images to gallery recyclerview using adapter
//            mGalleryAdapter.addGalleryItems(galleryItems);
        } else {
            //request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_READ_STORAGE);
        }

        materialDesignFAM = findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 = findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = findViewById(R.id.material_design_floating_action_menu_item4);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JuzActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(MainActivity.this, "Juz", Toast.LENGTH_LONG).show();
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PrestasiActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(MainActivity.this, "Prestasi", Toast.LENGTH_LONG).show();
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TargetActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(MainActivity.this, "Target", Toast.LENGTH_LONG).show();
            }
        });

        //Menampilkan target
        String tampil_target;

        tampiltarget = (TextView) findViewById(R.id.tampil_target);
        Intent intents = getIntent();
        tampil_target = intents.getStringExtra("TARGET");

        tampiltarget.setText("Target Anda " + tampil_target + " halaman");

        init();
        checkDirectory();

    }

    public void init() {
        List<BaseSurat> list = new ArrayList<BaseSurat>();
        String json = getAssetsJSON(JSON_LIST_SURAT);

        JSONArray array = null;
        try {
            array = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array.length() > 0) {
            Gson gson = new Gson();
            int i = 0;
            while (i < array.length()) {
                try {
                    list.add(gson.fromJson(array.getJSONObject(i).toString(), BaseSurat.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        } else {
            Toast.makeText(this, "No Objects", Toast.LENGTH_LONG).show();
        }


        listView = findViewById(R.id.listView);
        suratAdapter = new SuratAdapter(MainActivity.this, list);
        listView.setAdapter(suratAdapter);
    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void checkDirectory() {
        if (new CheckForSDCard().isSDCardPresent()) {

            apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + Utils.downloadDirectory);
        } else
            Toast.makeText(getApplicationContext(), "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

        //If File is not present create directory
        if (!apkStorage.exists()) {
            showDialogDownload();
        }
    }


    public void showDialogDownload() {
        builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage(R.string.dialog_message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        for (int i=0;i<pageSurah;i++){
                            int x = i +1;
                            if (x < 10){
                                new DownloadTask(MainActivity.this, progress, Utils.mainUrlImage+"00"+x+".png");
                            }
                            else if (x >= 10 && x <100){
                                new DownloadTask(MainActivity.this,progress, Utils.mainUrlImage+"0"+x+".png");
                            }
                            else if (x >= 100){
                                new DownloadTask(MainActivity.this, progress,Utils.mainUrlImage+x+".png");
                            }
                        }

                    }
                });
        //TODO
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //  Action for 'NO' Button
//                        dialog.cancel();
//                    }
//                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(R.string.dialog_title);
        alert.show();

    }

    public void onItemSelect(int position){
        //create fullscreen SlideShowFragment dialog
        SlideShowFragment slideShowFragment = SlideShowFragment.newInstance(position);
        //setUp style for slide show fragment
        slideShowFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentTheme);
        //finally show dialogue
        slideShowFragment.show(getSupportFragmentManager(), null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Get images
                galleryItems = GalleryUtils.getImages(this);
                // add images to gallery recyclerview using adapter
//                mGalleryAdapter.addGalleryItems(galleryItems);
            } else {
                Toast.makeText(this, "Storage Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Notifikasi keluar
    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah anda ingin keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
