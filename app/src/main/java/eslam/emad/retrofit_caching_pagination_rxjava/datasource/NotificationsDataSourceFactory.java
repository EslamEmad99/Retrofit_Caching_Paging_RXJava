package eslam.emad.retrofit_caching_pagination_rxjava.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import org.jetbrains.annotations.NotNull;

public class NotificationsDataSourceFactory extends DataSource.Factory {

    NotificationsDataSource notificationsDataSource;
    MutableLiveData<NotificationsDataSource> mutableLiveData;

    public NotificationsDataSourceFactory() {
        mutableLiveData= new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {

        notificationsDataSource = new NotificationsDataSource();
        mutableLiveData.postValue(notificationsDataSource);
        return notificationsDataSource;
    }

    public MutableLiveData<NotificationsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
