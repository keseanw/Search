package kesean.com.search.ui.specialblend;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.repository.SearchRepository;
import kesean.com.search.util.RunOn;
import kesean.com.search.util.SchedulerType;

/**
 * Created by Kesean on 2/5/18.
 */

public class SpecialPresenter implements SpecialContract.Presenter, LifecycleObserver {

    private SearchRepository repository;

    private SpecialContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposeBag;

    /*
   * Dependency Injecting Repository, View contract, IO & UI Schedulers
   * */
    @Inject
    public SpecialPresenter(SearchRepository repository, SpecialContract.View view,
                            @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler uiScheduler) {
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        // Initialize this presenter to be lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();
    }

    /*
   * Lifecycle annotations on lifecycle events
   * Method call to Load data
   * */
    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        loadSpecial(false);
    }

    /*
  * Clean up any no longer used resources here
  * */
    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposeBag.clear();
    }

    /*
    * Method call to Load Special Blend Data
    * */
    @Override
    public void loadSpecial(boolean onlineRequired) {
        // Clear old data on view
        view.clearSpecial();

        // Load new one and populate it into view
        Disposable disposable = repository.loadSearch()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError, () -> view.stopLoadingIndicator());
        disposeBag.add(disposable);
    }

    /*
    *  Method that updates a user's liked value to update in db - Updates will propogate to match tab as well
    * */
    @Override
    public void likeUser(Datum user, int position) {

        if(!user.getLiked()) {
            user.setLiked(true);
        }else{
            user.setLiked(false);
        }

        Observable.fromCallable(() -> repository.likeUser(user))
                .filter(userObj -> userObj != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(likedUser -> view.showHighlight(user, position));
    }

    /**
     * Updates view after loading data is completed successfully.
     */
    private void handleReturnedData(List<Datum> list) {
        view.stopLoadingIndicator();
        if (list != null && !list.isEmpty()) {
            view.showSpecial(list);
        } else {
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
