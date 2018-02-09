package kesean.com.search.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kesean.com.search.data.database.SearchDao;
import kesean.com.search.data.database.SearchDb;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

@Module
public class DatabaseModule {
    private static final String DATABASE = "database_name";

    /*
    * Database module
    * */

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    SearchDb provideSearchDb(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, SearchDb.class, databaseName).build();
    }

    @Provides
    @Singleton
    SearchDao provideSearchDao(SearchDb searchDb) {
        return searchDb.searchDao();
    }
}
