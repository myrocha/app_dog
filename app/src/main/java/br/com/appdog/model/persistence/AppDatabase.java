package br.com.appdog.model.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

import br.com.appdog.model.Url;

/**
 * Class that creates the DAO's of the project.
 */
@Singleton
@Database(entities = {Url.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UrlDAO urlDAO();


}
