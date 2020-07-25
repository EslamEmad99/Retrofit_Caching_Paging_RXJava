package eslam.emad.retrofit_caching_pagination_rxjava.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import eslam.emad.retrofit_caching_pagination_rxjava.datasource.NotificationsDataSource;
import eslam.emad.retrofit_caching_pagination_rxjava.datasource.NotificationsDataSourceFactory;
import eslam.emad.retrofit_caching_pagination_rxjava.model.Datum;

import static eslam.emad.retrofit_caching_pagination_rxjava.utils.Constants.PAGE_SIZE;

public class NotificationViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<Datum>> itemPagedList;
    private NotificationsDataSourceFactory notificationsDataSourceFactory;

    public NotificationViewModel() {
        notificationsDataSourceFactory = new NotificationsDataSourceFactory();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(PAGE_SIZE * 2)
                        .setPageSize(PAGE_SIZE)
                        .setPrefetchDistance(PAGE_SIZE)
                        .build();

        executor = Executors.newFixedThreadPool(5);
        itemPagedList = (new LivePagedListBuilder<Integer,Datum>(notificationsDataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();
    }

    LiveData<PagedList<Datum>> getItemPagedList() {
        return itemPagedList;
    }

    void refresh() {
        notificationsDataSourceFactory.getMutableLiveData().getValue().invalidate();
    }
}
