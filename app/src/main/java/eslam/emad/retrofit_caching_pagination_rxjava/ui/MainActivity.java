package eslam.emad.retrofit_caching_pagination_rxjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import eslam.emad.retrofit_caching_pagination_rxjava.R;
import eslam.emad.retrofit_caching_pagination_rxjava.model.Datum;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NotificationViewModel viewModel;
    private NotificationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.tool_bar));
        setTitle("Notifications");
        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        adapter = new NotificationsAdapter(getApplicationContext());

        viewModel.getItemPagedList().observe(this, new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> items) {
                adapter.submitList(items);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> Toast.makeText(MainActivity.this, String.valueOf(position+1), Toast.LENGTH_SHORT).show());

        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
    }
}