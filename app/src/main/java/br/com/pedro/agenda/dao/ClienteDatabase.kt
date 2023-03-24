package br.com.pedro.agenda.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.pedro.agenda.model.Cliente


@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDatabase : RoomDatabase() {

    companion object{

        @Volatile private lateinit var db: ClienteDatabase
        fun instancia(context: Context): ClienteDatabase{
            if(::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                ClienteDatabase::class.java, "Cliente.db"
            ).build()

            return db
        }
    }
    abstract fun clienteDatabase(): ClienteDao

}