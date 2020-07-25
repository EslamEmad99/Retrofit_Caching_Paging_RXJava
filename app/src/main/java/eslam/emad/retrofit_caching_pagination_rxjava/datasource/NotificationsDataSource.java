package eslam.emad.retrofit_caching_pagination_rxjava.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import eslam.emad.retrofit_caching_pagination_rxjava.data.ApiClient;
import eslam.emad.retrofit_caching_pagination_rxjava.model.Datum;
import eslam.emad.retrofit_caching_pagination_rxjava.model.NotificationModel;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static eslam.emad.retrofit_caching_pagination_rxjava.utils.Constants.API_TOKEN;

public class NotificationsDataSource extends PageKeyedDataSource<Integer, Datum> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {
        Single<NotificationModel> observable = ApiClient.getINSTANCE().getNotifications(API_TOKEN, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver<NotificationModel> observer = new SingleObserver<NotificationModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(NotificationModel model) {
                if (model != null) {
                    callback.onResult(model.getData().getData(), null, 2);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        observable.subscribe(observer);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
        Single<NotificationModel> observable = ApiClient.getINSTANCE().getNotifications(API_TOKEN, params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver<NotificationModel> observer = new SingleObserver<NotificationModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(NotificationModel model) {
                if (model.getData().getData().size() != 0 && model.getData().getData() != null) {
                    callback.onResult(model.getData().getData(), params.key + 1);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        observable.subscribe(observer);
    }
}
