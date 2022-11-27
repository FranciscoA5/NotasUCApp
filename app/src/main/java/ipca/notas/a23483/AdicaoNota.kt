package ipca.notas.a23483

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AdicaoNota : AppCompatActivity() {

    var nota : Double? = null
    var ano  : Int? = null
    var uc : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicao_nota)

        nota = intent.getDoubleExtra("nota", 0.0)
        ano = intent.getIntExtra("ano", 0)
        uc = intent.getStringExtra("uc")

        findViewById<EditText>(R.id.nota).setText(nota.toString())
        findViewById<EditText>(R.id.ano).setText(ano.toString())
        findViewById<EditText>(R.id.curso).setText(uc)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val intent = Intent()
            intent.putExtra("nota",findViewById<EditText>(R.id.nota).text.toString().toDouble())
            intent.putExtra("ano",findViewById<EditText>(R.id.ano).text.toString().toInt() )
            intent.putExtra("uc",findViewById<EditText>(R.id.curso).text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}