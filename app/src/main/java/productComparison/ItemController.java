package productComparison;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemController implements Callback<List<Item>>{

    static final String BASE_URL = "https://seminar-xi.vercel.app";

    private ItemCallBack itemCallBack;

    public ItemController(ItemCallBack itemCallBack) {
        this.itemCallBack = itemCallBack;
    }

    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private final ItemApi itemApi = retrofit.create(ItemApi.class);

    public void getAllMovies(){
        Call<List<Item>> call = itemApi.loadItems();
        call.enqueue(this);
    }

    public void getByAlt(int altNum){
        Call<List<Item>> call = itemApi.loadItemsByAlt(altNum);
        call.enqueue(this);
    }

    public void getByName(String name){
        Call<Item> call = itemApi.loadItemByName(name);
        call.enqueue(this);
    }

    public void getById(int id){
        Call<Item> call = itemApi.loadItemByItemId(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
        if (response.isSuccessful()){
            List<Item> items = response.body();
            itemCallBack.success(items);
        }else{
            itemCallBack.error(String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        itemCallBack.error(t.getMessage());
        t.printStackTrace();
    }
}
