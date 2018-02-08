package kesean.com.search.ui.match;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.repository.SearchRepository;
import kesean.com.search.ui.specialblend.SpecialContract;
import kesean.com.search.util.RunOn;
import kesean.com.search.util.SchedulerType;

/**
 * Created by Kesean on 2/7/18.
 */

public class MatchPresenter implements SpecialContract.MatchPresenter, LifecycleObserver {

    private SearchRepository repository;

    private SpecialContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposeBag;

    @Inject
    public MatchPresenter(SearchRepository repository, SpecialContract.View view,
                          @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler uiScheduler) {
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        loadMatches();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposeBag.clear();
    }

    @Override
    public void loadMatches() {
        // Clear old data on view
        view.clearSpecial();

        // Load new one and populate it into view
        Disposable disposable = repository.getMatches()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError, () -> view.stopLoadingIndicator());
        disposeBag.add(disposable);
    }

    /**
     * Updates view after loading data is completed successfully.
     */
    private void handleReturnedData(List<Datum> list) {
        view.stopLoadingIndicator();
        if (list != null && !list.isEmpty()) {
            view.showSpecial(list);
        } else {
            view.clearSpecial();
            view.showNoDataMessage();
        }
    }

    /**
     * Updates view if there is an error after loading data from repository.
     */
    private void handleError(Throwable error) {
        view.stopLoadingIndicator();
        view.showErrorMessage(error.getLocalizedMessage());
    }
}