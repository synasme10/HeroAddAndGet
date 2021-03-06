package com.e.heroaddandget;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import HeroAPI.HeroAPI;
import URL.BaseUrl;
import model.ImageResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroAPIActivity extends AppCompatActivity {

    EditText Etname,Etdesc;
    Button BtnHero;
    ImageView Imgheroimage;
    String imagePath, imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_api);

        Etname=findViewById(R.id.Etname);
        Etdesc=findViewById(R.id.Etdesc);
        Imgheroimage=findViewById(R.id.Imghero);
//        loadFromURL();
        BtnHero=findViewById(R.id.Btnhero);

        Imgheroimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        BtnHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }

    private void BrowseImage() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if(data==null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }

        Uri uri=data.getData();
        imagePath=getRealPathFromUri(uri);
        previewImage(imagePath);
    }

    private void previewImage(String imagePath) {
        File imgFile=new File(imagePath);
        if (imgFile.exists()){
            Bitmap mybitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Imgheroimage.setImageBitmap(mybitmap);
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int colIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(colIndex);
        cursor.close();
        return result;
    }


    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
  StrictMode.setThreadPolicy(policy);
    }

    private void SaveImageOnly(){

        File file=new File(imagePath);

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);

        HeroAPI heroAPI=BaseUrl.getInstance().create(HeroAPI.class);
        Call<ImageResponse> responseBodyCall=heroAPI.uploadImage(body);
        StrictMode();
        try{
            Response<ImageResponse> imageResponseResponse=responseBodyCall.execute();
            imageName=imageResponseResponse.body().getFilename();
        }
        catch (IOException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



//    private void loadFromURL() {
//StrictMode();
//try {
//    String imgURl="https://softwarica.edu.np/wp-content/uploads/2019/02/Kiran-Rana.jpg";
//    URL url=new URL(imgURl);
//    Imgheroimage.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
//}catch (IOException e){
//    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//}
//    }

    private void Add() {
        SaveImageOnly();
        String name=Etname.getText().toString();
        String desc=Etdesc.getText().toString();
        Map<String, String> map=new HashMap<>();
        map.put("name",name);
        map.put("desc",desc);
        map.put("image",imageName);

        HeroAPI heroAPI=BaseUrl.getInstance().create(HeroAPI.class);
        Call<Void> heroesCall=heroAPI.addHero(map);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(HeroAPIActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(HeroAPIActivity.this, "Successfully Hero is added", Toast.LENGTH_SHORT).show();
                Etname.setText("");
                Etdesc.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(HeroAPIActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent=new Intent(HeroAPIActivity.this,HeroViewActivity.class);
        startActivity(intent);
        finish();
    }
}
