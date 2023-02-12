package br.com.pedro.agenda.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val endreco: String,
    val email: String,
    val telefone: String
): Parcelable {
}