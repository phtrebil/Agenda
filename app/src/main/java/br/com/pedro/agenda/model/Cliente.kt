package br.com.pedro.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val endreco: String,
    val email: String,
    val telefone: String
) {
}