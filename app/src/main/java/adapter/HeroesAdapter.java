package adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heroaddandget.HeroViewActivity;
import com.e.heroaddandget.R;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import URL.BaseUrl;
import model.Heroesmodel;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>{
private List<Heroesmodel> heroesList;
private Context context;

    public HeroesAdapter(List<Heroesmodel> heroesList, Context context) {
        this.heroesList = heroesList;
        this.context = context;
    }

    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_hero_view,viewGroup,false);

        return new HeroesViewHolder(view);

    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesViewHolder heroesViewHolder, int i) {
Heroesmodel heroesmodel=heroesList.get(i);
String imgpath= BaseUrl.Base_Url+"uploads/"+heroesmodel.getImage();
StrictMode();
try{
    URL url=new URL(imgpath);
   heroesViewHolder.imgPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
} catch (Exception e) {
    e.printStackTrace();
}

heroesViewHolder.tvname.setText(heroesmodel.getName());
heroesViewHolder.tvdesc.setText(heroesmodel.getDesc()   );
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HeroesViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPhoto;
        TextView tvname,tvdesc;
        public HeroesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            tvname=itemView.findViewById(R.id.tvName);
            tvdesc=itemView.findViewById(R.id.tvdesc);


        }
    }
}
