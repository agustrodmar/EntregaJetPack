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
/**
 * Muestra un botón y una línea de progreso. El texto del botón cambia en función de si se está mostrando la línea de progreso.
 */
@Preview(showBackground = true)
@Composable
fun Actividad01() {
    // Aquí estoy declarando y recordando el estado de `showLoading`. Inicialmente es falso.
    var showLoading by rememberSaveable { mutableStateOf(false) }
    // Aquí estoy declarando y recordando el estado de `textoBoton`. Inicialmente es "Cargar perfil".
    var textoBoton by rememberSaveable { mutableStateOf("Cargar perfil") }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Si `showLoading` es verdadero, cambio `textoBoton` a "Cancelar" y muestro la línea de progreso.
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
            // Si `showLoading` es falso, cambio `textoBoton` a "Cargar perfil".
            textoBoton = "Cargar perfil"
        }

        // Aquí estoy declarando un botón. Cuando se hace clic en él, cambio el valor de `showLoading`.
        Button(
            onClick = { showLoading = !showLoading }
        ) {
            // El texto del botón es el valor de `textoBoton`.
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
/**
 * Esta es una vista previa de la Composable `Actividad02`.
 * Muestra un botón y una línea de progreso. El texto del botón cambia en función de si se está mostrando la línea de progreso.
 * Además, la separación entre el botón y la línea de progreso también cambia en función de si se está mostrando la línea de progreso.
 */
@Preview(showBackground = true)
@Composable
fun Actividad02() {
    // Aquí estoy declarando y recordando el estado de `showLoading`. Inicialmente es falso.
    var showLoading by rememberSaveable { mutableStateOf(false) }
    // Aquí estoy declarando y recordando el estado de `texto`. Inicialmente es "Cargar perfil".
    var texto by rememberSaveable { mutableStateOf("Cargar perfil")}

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Si `showLoading` es verdadero, cambio `texto` a "Cancelar" y muestro la línea de progreso.
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
            // Si `showLoading` es falso, cambio `texto` a "Cargar perfil".
            texto = "Cargar Perfil"
        }

        // Aquí estoy declarando un botón. Cuando se hace clic en él, cambio el valor de `showLoading`.
        // Además, la separación entre el botón y la línea de progreso cambia en función de si se está mostrando la línea de progreso.
        Button(
            modifier = Modifier.padding(top = if (showLoading) 30.dp else 0.dp),
            onClick = { showLoading = !showLoading }
        ) {
            // El texto del botón es el valor de `texto`.
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
/**
 * Muestra dos botones y una línea de progreso. Los botones incrementan y decrementan el progreso de la línea de progreso.
 * Además, los botones están separados entre sí y de la línea de progreso.
 */
@Preview(showBackground = true)
@Composable
fun Actividad03() {
    // Aquí estoy declarando y recordando el estado de `progreso`. Inicialmente es 0.
    var progreso by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Aquí estoy mostrando una línea de progreso con el valor de `progreso`.
        LinearProgressIndicator(progress = progreso)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center) {
            // Aquí estoy declarando un botón. Cuando se hace clic en él, incremento el valor de `progreso` en 0.1.
            // Uso `coerceAtMost(1f)` para asegurarme de que `progreso` no exceda 1.
            Button(onClick = { progreso  = (progreso + 0.1f).coerceAtMost(1f)}) {
                Text(text = "Incrementar")
            }

            // Aquí estoy añadiendo un espacio de 10.dp entre los botones.
            Spacer(modifier = Modifier.padding(10.dp))

            // Aquí estoy declarando otro botón. Cuando se hace clic en él, decremento el valor de `progreso` en 0.1.
            // Uso `coerceAtLeast(0f)` para asegurarme de que `progreso` no sea menor que 0.
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
/**
 * Muestra un TextField en el centro de la pantalla. El TextField reemplaza el valor de una coma por un punto
 * y no permite escribir más de un punto decimal.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Actividad04() {
    // Aquí estoy declarando y recordando el estado de `valorTexto`. Inicialmente es una cadena vacía.
    var valorTexto by rememberSaveable { mutableStateOf("") }
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        // Aquí estoy declarando un TextField. Cuando se cambia su valor, reemplazo las comas por puntos y evito que se escriban más de un punto decimal.
        TextField(
            value = valorTexto,
            onValueChange = { textoIngresado ->
                // Si `textoIngresado` contiene una coma, la reemplazo por un punto. De lo contrario, dejo `textoIngresado` como está.
                val nuevoTexto = if(textoIngresado.contains(',')) textoIngresado.replace(',','.') else textoIngresado
                // Si `nuevoTexto` no contiene dos puntos seguidos y tiene a lo sumo un punto, actualizo `valorTexto` con `nuevoTexto`.
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
/**
 * Eleva el estado de `value` a esta función y pasa el valor y la función de cambio a `Actividad5`.
 */
@Preview
@Composable
fun PreActividad5(){
    // Aquí estoy declarando y recordando el estado de `value`. Inicialmente es una cadena vacía.
    var value by rememberSaveable { mutableStateOf("") }
    // Aquí estoy pasando `value` y una función de cambio a `Actividad5`.
    Actividad5( value, onValueChange = {newValue ->
        // Si `newValue` contiene una coma, la reemplazo por un punto.
        if (newValue.contains(',')) {
            value = newValue.replace(',', '.')
        }
        // Si `newValue` tiene a lo sumo un punto y todos los caracteres restantes son dígitos, actualizo `value` con `newValue`.
        if (newValue.count { c: Char -> c == '.' } <= 1 && newValue.replace(".","").isDigitsOnly()){
            value = newValue
        } else if (newValue.count { c: Char -> c == '.' } > 1) {
            // Si `newValue` tiene más de un punto, elimino el último punto y actualizo `value`.
            value = newValue.substring(0, newValue.lastIndexOf('.'))
        }
    })
}

/**
 * Muestra un OutlinedTextField en el centro de la pantalla. El OutlinedTextField reemplaza el valor de una coma por un punto
 * y no permite escribir más de un punto decimal. Además, tiene un padding alrededor de 15.dp y colores diferentes en los bordes
 * cuando tiene el foco y no lo tiene.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad5(string : String,
               onValueChange : (String) -> Unit ) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        // Aquí estoy declarando un OutlinedTextField. Cuando se cambia su valor, llamo a `onValueChange`.
        // Además, tiene un padding alrededor de 15.dp y colores diferentes en los bordes cuando tiene el foco y no lo tiene.
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
