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
import kesean.com.search.data.model.Search;
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

    @Inject
    public SpecialPresenter(SearchRepository repository, SpecialContract.View view,
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

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        loadSpecial(false);
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        // Clean up any no-longer-use resources here
        disposeBag.clear();
    }

    @Override
    public void loadSpecial(boolean onlineRequired) {
        // Clear old data on view
        view.clearSpecial();

        // Load new one and populate it into view
        Disposable disposable = repository.loadSearch(onlineRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError);
        //, () -> view.stopLoadingIndicator()
        disposeBag.add(disposable);
    }

    @Override
    public void likeUser(Datum user, int position) {

        if(!user.getLiked()) {
            user.setLiked(true);
        }else{
            user.setLiked(false);
        }

        Observable.fromCallable(() -> repository.likeUser(user))
                .filter(userLikeBool -> userLikeBool != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(likedUser -> view.showHighlight(user, position));
        //disposeBag.add(disposable);
    }

//    private boolean getNewLikedBool(boolean likeVal) {
//        if(likeVal) {
//            return false;
//        }else{
//            return true;
//        }
//    }

    /**
     * Updates view after loading data is completed successfully.
     */
    private void handleReturnedData(List<Datum> list) {
        //view.stopLoadingIndicator();
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
