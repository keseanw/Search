package kesean.com.search.ui.specialblend;

import java.util.List;

import kesean.com.search.data.model.Datum;
import kesean.com.search.ui.base.BasePresenter;

/**
 * Created by Kesean on 2/5/18.
 */

public interface SpecialContract {

    interface View {
        void showSpecial(List<Datum> search);

        void clearSpecial();

        void showNoDataMessage();

        void showErrorMessage(String error);

        void showHighlight(Datum likedUser, int position);

        void stopLoadingIndicator();

        void showEmptySearchResult();
    }

    /*
    * Presenter for SpecialBlend tab
    * */
    interface Presenter extends BasePresenter<SpecialContract.View> {

        void loadSpecial();

        void likeUser(Datum user, int position);

    }

    /*
    * Presenter for matches tab
    * */
    interface MatchPresenter extends BasePresenter<SpecialContract.View> {

        void loadMatches();
    }
}
