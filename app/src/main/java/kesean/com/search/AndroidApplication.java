package kesean.com.search;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import kesean.com.search.data.DaggerSearchRepositoryComponent;
import kesean.com.search.data.SearchRepositoryComponent;
import timber.log.Timber;

/**
 * Created by Kesean on 2/4/18.
 */

public class AndroidApplication extends Application{

    private SearchRepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger module init
        initializeDependencies();

        if (BuildConfig.DEBUG) {
            //Timber logging & Stetho setup
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }

        //leak canary for memory leaks
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initializeDependencies() {
        repositoryComponent = DaggerSearchRepositoryComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public SearchRepositoryComponent getSearchRepositoryComponent() {
        return repositoryComponent;
    }
}
