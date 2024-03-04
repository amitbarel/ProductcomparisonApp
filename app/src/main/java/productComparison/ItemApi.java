package productComparison;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemApi {

    @GET("/items")
    Call<List<Item>> loadItems();

    @GET("/items")
    Call<List<Item>> loadItemsByAlt(@Query("alternative") int alt);

    @GET("/items")
    Call<Item> loadItemByItemId(@Query("item_id") int itemId);

    @GET("/items")
    Call<Item> loadItemByName(@Query("name") String name);
}
