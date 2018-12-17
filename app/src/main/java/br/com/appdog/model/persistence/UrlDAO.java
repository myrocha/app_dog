package br.com.appdog.model.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.appdog.model.Url;

@Dao
public interface UrlDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Url url);

    @Query("SELECT * FROM Url WHERE category IN (:category)")
    List<Url> getUrlByCategory(final String category);





}
