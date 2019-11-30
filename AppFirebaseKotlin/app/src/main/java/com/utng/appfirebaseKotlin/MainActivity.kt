package com.utng.appfirebaseKotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // se declara la base de datos
    var refDiario: DatabaseReference = FirebaseDatabase.getInstance().getReference("Clase")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // delcarar objetos para recibir elementos de la vista


         var btnReg: Button? = null
        // referencia a base de datos

        btnRegistrar.setOnClickListener { registrarClase() }



    }

    private fun registrarClase(){



        var comentario:String = spiCarrera.selectedItem.toString()
        var liga:String = spiMateria.selectedItem.toString()
        var equipo:String = edtTema.text.toString()
        // Title is required
        if (!TextUtils.isEmpty(equipo)) {
            var id:String = ""
            id = refDiario.push().key.toString()

            val leccion = ClaseK(claseId = id, equipo = equipo, liga = liga, comentario = comentario)
            // se agrega un hijo dentro de la rama de lecciones
            if (id != null) {
                refDiario.child("Equipos").child(id).setValue(leccion)
                Toast.makeText(this, "equipo registrado", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Ingresa equipo", Toast.LENGTH_SHORT).show()
        }
    }

}
