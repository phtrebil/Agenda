package br.com.pedro.agenda.dao

import androidx.room.*
import br.com.pedro.agenda.model.Cliente

@Dao
interface ClienteDao {
    @Query("SELECT * FROM cliente")
    fun getAll(): List<Cliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(vararg clientes: Cliente)

    @Delete
    fun delete(cliente: Cliente)

}