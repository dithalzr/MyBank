package com.dicoding.picodiploma.mybank.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.mybank.db.ClientDatabase.Companion.getInstance
import com.dicoding.picodiploma.mybank.db.DaoDatabase
import com.dicoding.picodiploma.mybank.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    var totalSaldo: LiveData<Int>
    var dataBank: LiveData<List<ModelDatabase>>
    var daoDatabase: DaoDatabase?

    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            daoDatabase?.deleteSingleData(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        daoDatabase = getInstance(application)?.appDatabase?.daoDatabase()
        dataBank = daoDatabase!!.getAll()
        totalSaldo = daoDatabase!!.getSaldo()
    }

}
