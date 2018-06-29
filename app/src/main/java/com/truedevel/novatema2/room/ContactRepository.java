package com.truedevel.novatema2.room;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;
import com.truedevel.novatema2.Contact;
import com.truedevel.novatema2.room.core.ContactDao;
import com.truedevel.novatema2.room.core.Database;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public ContactRepository(Context application){
        Database db = Database.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getAll();
    }
    private Completable insert_first(List<Contact> contact){
        return Completable.fromAction(() -> {
            mContactDao.insertAllContacts(contact);
        });
    }
    public  void insertEndMethod(List<Contact> contact){
        mDisposable.add(insert_first(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()-> Log.e("Log","Update"),
                        throwable -> Log.e("Log","Unable to update username", throwable)));
    }
    public void unsubscribe(){
        if(!mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }
}