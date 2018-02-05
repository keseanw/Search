package kesean.com.search.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import kesean.com.search.data.Config;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

@Dao
public interface SearchDao {

    @Query("SELECT * FROM " + Config.SEARCH_TABLE_NAME)
    Flowable<List<Search>> getAllSearch();

    //create a more fine grained model for view?
    //not in use
    @Query("SELECT * FROM " + Config.SEARCH_TABLE_NAME + " WHERE id == :id")
    Flowable<Search> getSearchById(int id);

    //update liked field based on userId or some other primary key
    //not in use
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Search search);

    //get matches that are liked
    //not in use
    @Query("DELETE FROM " + Config.SEARCH_TABLE_NAME)
    void deleteAll();
}

