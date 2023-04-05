package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.pedro.agenda.R
import br.com.pedro.agenda.databinding.ActivityBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.fragments.DetalhesFragment
import br.com.pedro.agenda.ui.fragments.FormularioFragments
import br.com.pedro.agenda.ui.fragments.ListaDeClientesFragment
import kotlinx.android.synthetic.main.fragments_formulario.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val PERMISSAO_GALERIA = android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    private val binding by lazy {
        ActivityBinding.inflate(layoutInflater)
    }

    private lateinit var dialog: AlertDialog

    private val resultGaleria =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data?.data != null) {
                val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(
                        baseContext.contentResolver,
                        result.data?.data
                    )
                } else {
                    val fonte = ImageDecoder.createSource(
                        this.contentResolver,
                        result.data?.data!!
                    )
                    ImageDecoder.decodeBitmap(fonte)
                }
                imagem_add_cliente.setImageBitmap(bitmap)
            }
        }

    private val requestGaleria =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permissao ->
            if (permissao) {
                resultGaleria.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                )
            } else {
                mostraDialogDePermissao()
            }

        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(savedInstanceState == null){
            abreListaNoticias()
        }
    }

    private fun abreListaNoticias() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main_activity, ListaDeClientesFragment())
        transaction.commit()
    }

    private fun configuraFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main_activity, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)

        when(fragment){
            is ListaDeClientesFragment -> {
                configuraListaDeClientesFragment(fragment)
            }

            is DetalhesFragment -> {
                configuraDetalhesFragment(fragment)
            }

            is FormularioFragments -> {
                configuraFormularioFragment(fragment)
                fragment.adicionaImagemGaleria = {
                    pegaImagemNaGaleria()
                }
            }
        }

    }

    private fun pegaImagemNaGaleria() {
        verificaPermissaoGaleria()
    }

    private fun configuraFormularioFragment(fragment: FormularioFragments) {
        fragment.vaiParaListaDeClientesFragment = this::vaiParaListaDeClientesFragment
    }

    private fun configuraDetalhesFragment(fragment: DetalhesFragment) {
        fragment.vaiParaFormulario = this::vaiParaFormularioPreenchido
    }

    private fun configuraListaDeClientesFragment(fragment: ListaDeClientesFragment) {
        fragment.vaiParaFormularioActivity = this::vaiParaFormularioFragment
        fragment.vaiParaDetalhesActivity = this::vaiParaDetalhesActivity
    }

    private fun vaiParaDetalhesActivity(cliente: Cliente) {
        val fragment = DetalhesFragment()
        val dados = Bundle()
        dados.putParcelable("cliente", cliente)
        fragment.arguments = dados
        configuraFragment(fragment)
    }
    private fun vaiParaFormularioFragment() {
        configuraFragment(FormularioFragments())
    }
    private fun vaiParaListaDeClientesFragment() {
        configuraFragment(ListaDeClientesFragment())
    }
    private fun vaiParaFormularioPreenchido(cliente: Cliente) {
        val fragment = FormularioFragments()
        val dados = Bundle()
        dados.putParcelable("cliente2", cliente)
        fragment.arguments = dados
        configuraFragment(fragment)
    }

    private fun mostraDialogDePermissao() {
        val builde = AlertDialog.Builder(this)
            .setTitle("Atenção")
            .setMessage("Precisamos do acesso a galeria do dispositivo, deseja permitir agora?")
            .setNegativeButton("Não") { _, _
                ->
                dialog.dismiss()
            }
            .setPositiveButton("Sim") { _, _ ->
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null)
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                dialog.dismiss()
            }
        dialog = builde.create()
        dialog.show()
    }

    private fun verificaPermissaoGaleria() {
        val permissaoGaleriaAceita = verificaPermissao(PERMISSAO_GALERIA)

        when {
            permissaoGaleriaAceita -> {

                resultGaleria.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                )
            }
            shouldShowRequestPermissionRationale(PERMISSAO_GALERIA) -> mostraDialogDePermissao()

            else -> requestGaleria.launch(PERMISSAO_GALERIA)

        }

    }

    private fun verificaPermissao(permissaoGaleria: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permissaoGaleria
        ) == PackageManager.PERMISSION_GRANTED
    }


}