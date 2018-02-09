package kesean.com.search.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

    /*
    * Room Db setup
    * */
@Database(entities = Datum.class, version = 1)
public abstract class SearchDb extends RoomDatabase{

    public abstract SearchDao searchDao();

}
