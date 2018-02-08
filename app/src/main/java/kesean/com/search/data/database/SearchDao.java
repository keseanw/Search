package kesean.com.search.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import kesean.com.search.data.Config;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

@Dao
public interface SearchDao {

    @Query("SELECT * FROM " + Config.SEARCH_TABLE_NAME)
    Flowable<List<Datum>> getAllSearch();

    //not in use
    @Query("SELECT * FROM " + Config.SEARCH_TABLE_NAME + " WHERE mUserid == :id")
    Flowable<Datum> getSearchById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Datum search);

    //not in use
    @Query("DELETE FROM " + Config.SEARCH_TABLE_NAME)
    void deleteAll();

    @Update
    int update(Datum user);

    @Query("SELECT * FROM " + Config.SEARCH_TABLE_NAME + " WHERE mLiked = 1 ORDER BY mMatch DESC LIMIT 6")
    Flowable<List<Datum>> getMatches();
}

