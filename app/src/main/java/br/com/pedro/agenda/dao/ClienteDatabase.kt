package br.com.pedro.agenda.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.pedro.agenda.model.Cliente


@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDatabase : RoomDatabase() {


    abstract fun clienteDatabase(): ClienteDao

    open fun getInstance(context: Context?): ClienteDatabase? {
        return databaseBuilder(context!!, ClienteDatabase::class.java, "Agenda.db")
            .allowMainThreadQueries()
            .build()
    }
}