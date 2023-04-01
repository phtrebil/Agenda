package br.com.pedro.agenda.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.pedro.agenda.model.Cliente


@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDatabase : RoomDatabase() {

    companion object{

        @Volatile private  var db: ClienteDatabase? = null
        fun instancia(context: Context): ClienteDatabase{

            return db ?: databaseBuilder(
                context,
                ClienteDatabase::class.java, "Cliente.db"
            ).build()
                .also {
                    db = it
                }


        }
    }
    abstract fun clienteDatabase(): ClienteDao

}