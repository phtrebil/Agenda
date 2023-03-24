package br.com.pedro.agenda.dao

import androidx.room.*
import br.com.pedro.agenda.model.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Query("SELECT * FROM cliente")
    fun getAll(): Flow<List<Cliente>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(vararg clientes: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

}