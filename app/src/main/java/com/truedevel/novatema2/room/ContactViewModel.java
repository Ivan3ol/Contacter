package com.truedevel.novatema2.room;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;



public class ContactViewModel extends AndroidViewModel{
    public ContactRepository mRepository;
    @Override
    protected void onCleared(){
        mRepository.unsubscribe();
        super.onCleared();
    }
    public ContactViewModel(Application application){
        super(application);
        mRepository = new ContactRepository(application);
    }
    public ContactRepository getmRepository() {
        return mRepository;
    }
}
