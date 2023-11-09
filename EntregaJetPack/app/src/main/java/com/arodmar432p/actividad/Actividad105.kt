@file:Suppress("SpellCheckingInspection")

package com.arodmar432p.actividad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

/*
Actividad 1:
Hacer que el texto del botón muestre "Cargar perfil", pero cambie a "Cancelar"
cuando se muestre la línea de progreso... Cuando pulsemos "Cancelar" vuelve al texto por defecto.
*/
@Preview(showBackground = true)
@Composable
fun Actividad01() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var textoBoton by rememberSaveable { mutableStateOf("Cargar perfil") }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            textoBoton = "Cancelar"
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        } else {
            textoBoton = "Cargar perfil"
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = textoBoton)
        }
    }
}

/*
Actividad 2:
Modifica ahora también que se separe el botón de la línea de progreso 30 dp,
pero usando un estado... es decir, cuando no sea visible no quiero que tenga la separación
aunque no se vea.
*/
@Preview(showBackground = true)
@Composable
fun Actividad02() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var texto by rememberSaveable { mutableStateOf("Cargar perfil")}

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            texto = "Cancelar"
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        } else {
            texto = "Cargar Perfil"
        }

        Button(
            modifier = Modifier.padding(top = if (showLoading) 30.dp else 0.dp),
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = texto )
        }
    }
}

/*
Actividad 3:
- Separar los botones entre ellos 10 dp, del indicador de progreso 15 dp y centrarlos horizontalmente.
- Cuando se clique el botón Incrementar, debe añadir 0.1 a la propiedad progress y quitar 0.1
  cuando se pulse el botón Decrementar.
- Evitar que nos pasemos de los márgenes de su propiedad progressStatus (0-1)
*/
@Preview(showBackground = true)
@Composable
fun Actividad03() {
    var progreso by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = progreso)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { progreso  = (progreso + 0.1f).coerceAtMost(1f)}) {
                Text(text = "Incrementar")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { progreso = (progreso - 0.1f).coerceAtLeast(0f)}) {
                Text(text = "Decrementar")
            }
        }
    }
}

/*
Actividad 4:
Sitúa el TextField en el centro de la pantalla y haz que reemplace el valor de una coma por un punto
y que no deje escribir más de un punto decimal...
*/
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Actividad04() {
    var valorTexto by rememberSaveable { mutableStateOf("") }
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        TextField(
            value = valorTexto,
            onValueChange = { textoIngresado ->
                val nuevoTexto = if(textoIngresado.contains(',')) textoIngresado.replace(',','.') else textoIngresado
                if(!nuevoTexto.contains("..") && nuevoTexto.count{c:Char -> c == '.'} <= 1){
                    valorTexto = nuevoTexto
                }
            },
            label = { Text(text = "Importe") }
        )
    }
}

/*
Actividad 5:
Haz lo mismo, pero elevando el estado a una función superior y usando un componente OutlinedTextField
al que debes añadir un padding alrededor de 15 dp y establecer colores diferentes en los bordes
cuando tenga el foco y no lo tenga.
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.
*/
/*
Actividad 5:
Haz lo mismo, pero elevando el estado a una función superior y usando un componente OutlinedTextField
al que debes añadir un padding alrededor de 15 dp y establecer colores diferentes en los bordes
cuando tenga el foco y no lo tenga.
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.
*/
@Preview
@Composable
fun PreActividad5(){
    var value by rememberSaveable { mutableStateOf("") }
    Actividad5( value, onValueChange = {newValue ->
        if (newValue.contains(',')) {
            value = newValue.replace(',', '.')
        }
        if (newValue.count { c: Char -> c == '.' } <= 1 && newValue.replace(".","").isDigitsOnly()){
            value = newValue
        } else if (newValue.count { c: Char -> c == '.' } > 1) {
            value = newValue.substring(0, newValue.lastIndexOf('.'))
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad5(string : String,
               onValueChange : (String) -> Unit ) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        OutlinedTextField(
            value = string,
            onValueChange = { onValueChange(it) },
            label = { Text(text = "Importe") },
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Blue
            )
        )
    }
}