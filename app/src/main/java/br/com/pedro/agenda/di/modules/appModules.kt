package br.com.pedro.agenda.di.modules

import androidx.room.Room
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.ui.viewmodel.FormularioViewModel
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<ClienteDatabase> {
        Room.databaseBuilder(
            get(),
            ClienteDatabase::class.java,
            "Cliente.db"
        ).build()
    }

    viewModel<ListaDeClientesViewModel> {
        ListaDeClientesViewModel(get())
    }

    viewModel<FormularioViewModel> {
        FormularioViewModel(get())
    }
}
