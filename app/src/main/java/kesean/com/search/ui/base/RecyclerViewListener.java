package kesean.com.search.ui.base;

import android.view.View;

/**
 * Created by Kesean on 2/5/18.
 */

public interface RecyclerViewListener {

    @FunctionalInterface
    interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    @FunctionalInterface
    interface OnItemLongClickListener {
        void OnItemLongClick(View view, int position);
    }
}
