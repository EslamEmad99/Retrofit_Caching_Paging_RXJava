package eslam.emad.retrofit_caching_pagination_rxjava.data;

import eslam.emad.retrofit_caching_pagination_rxjava.model.NotificationModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("notifications")
    Single<NotificationModel> getNotifications(@Query("api_token") String api_token,
                                             @Query("page") int page);
}
