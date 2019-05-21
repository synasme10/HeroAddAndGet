package HeroAPI;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeroAPI {

    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@Field("name") String name,@Field("desc") String desc);

    //Using Fieldmap
 @FormUrlEncoded
            @POST("heroes")
    Call<Void> addHero(@FieldMap Map<String,String> map);
}
