package kesean.com.search.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kesean.com.search.AndroidApplication;
import kesean.com.search.R;
import kesean.com.search.data.SearchRepositoryComponent;

public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected SearchRepositoryComponent getSearchRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getSearchRepositoryComponent();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
