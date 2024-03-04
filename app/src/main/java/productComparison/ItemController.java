package productComparison;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemController implements Callback<List<Item>> {

    static final String BASE_URL = "https://seminar-xi.vercel.app";
    private ItemApi itemApi;
    private ItemCallBack itemCallBack;

    public ItemController(ItemCallBack itemCallBack) {
        this.itemCallBack = itemCallBack;

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.itemApi = retrofit.create(ItemApi.class);
    }

    public String getBaseUrl(){
        return BASE_URL;
    }

    public void getAllItems() {
        Call<List<Item>> call = itemApi.loadItems();
        call.enqueue(this);
    }

    public void getByAlt(int altNum) {
        Call<List<Item>> call = itemApi.loadItemsByAlt(altNum);
        call.enqueue(this);
    }

    public void getByName(String name) {
        Call<Item> call = itemApi.loadItemByName(name);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    Item item = response.body();
                    itemCallBack.success(item);
                } else {
                    itemCallBack.error(String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                itemCallBack.error(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void getById(int id) {
        Call<Item> call = itemApi.loadItemByItemId(id);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    Item item = response.body();
                    itemCallBack.success(item);
                } else {
                    itemCallBack.error(String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                itemCallBack.error(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
        if (response.isSuccessful()) {
            List<Item> items = response.body();
            itemCallBack.success(items);
        } else {
            itemCallBack.error(String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        itemCallBack.error(t.getMessage());
        t.printStackTrace();
    }

}
