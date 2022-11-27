package ipca.notas.a23483

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.BaseAdapter
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var notas = arrayListOf<CriarNotas>()
    val notasAdapter = NotasAdapter()

    var resultLauncher : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAdd = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        val nota1 = CriarNotas(12.7, "math", 1)
        notas.add(nota1)

        val notasview = findViewById<ListView>(R.id.notasvista)
        notasview.adapter = notasAdapter

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){

                val nota = it.data?.getDoubleExtra("nota", 0.0)
                val ano = it.data?.getIntExtra("ano",0)
                val uc = it.data?.getStringExtra("uc")
                notas.add(CriarNotas(nota?:0.0,uc?:"",ano?:0))

                notasAdapter.notifyDataSetChanged()

            }
        }

        buttonAdd.setOnClickListener {
            resultLauncher?.launch(Intent(this@MainActivity,AdicaoNota::class.java))
        }
    }


    inner class NotasAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return notas.size
        }

        override fun getItem(p0: Int): Any {
            return notas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }


        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_item, p2, false)

            val nota = rootView.findViewById<TextView>(R.id.gradeView)
            val ano = rootView.findViewById<TextView>(R.id.anoView)
            val curso = rootView.findViewById<TextView>(R.id.cursoView)

            nota.text = notas[p0].nota.toString()
            ano.text = notas[p0].ano.toString()
            curso.text = notas[p0].uc

            val bConstrain = rootView.findViewById<ConstraintLayout>(R.id.buttonConstrain)
            bConstrain.setOnClickListener {
                val intent = Intent(this@MainActivity, AdicaoNota::class.java)
                startActivity(intent)
            }

            return rootView
        }

    }
}