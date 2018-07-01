package com.truedevel.novatema2.room.core;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.truedevel.novatema2.Contact;
import java.util.List;

@Dao
public interface ContactDao {
    @Insert()
    void insertAllContacts(List<Contact> people);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);
    @Update
    void update(Contact contact);
    @Delete
    void delete(Contact contact);

    @Query("SELECT * FROM Contacts")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM Contacts WHERE phone = :phone")
    Contact getByPhone(String phone);

    @Query("DELETE FROM Contacts WHERE phone = :phone")
    void  deleteByPhone(String phone);

}