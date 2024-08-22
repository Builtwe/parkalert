package com.builtwe.parkalert.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.builtwe.parkalert.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        Column {
            ImageComponent(image = R.drawable.flag)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                MyTextField(labelVal = "email ID", icon = R.drawable.flag)
                Spacer(modifier = Modifier.height(15.dp))
                MyTextField(labelVal = "full name", icon = R.drawable.flag)
                Spacer(modifier = Modifier.height(15.dp))
                MyTextField(labelVal = "mobile", icon = R.drawable.flag)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyButton(labelVal = "Submit", navController = navController)
                    Spacer(modifier = Modifier.height(30.dp))
                    BottomSignupTextComponent(navController)
                }
            }

        }
    }
}

@Composable
fun BottomSignupTextComponent(navController: NavHostController) {
    val initialText = "Уже есть аккаунт? "
    val loginText = "Войди"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                if (span.item == "Log In") {
                    navController.navigate("LoginScreen")
                }
            }
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelVal: String, icon: Int) {
    var textVal by remember {
        mutableStateOf("")
    }
    val typeOfKeyboard: KeyboardType = when (labelVal) {
        "email ID" -> KeyboardType.Email
        "mobile" -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    OutlinedTextField(
        value = textVal,
        onValueChange = {
            textVal = it
        },
        label = { Text(text = labelVal) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal)
        },
//        leadingIcon = {
//            Icon(
//                painter = painterResource(id = icon),
//                contentDescription = "at_symbol"
//            )
//        },
        keyboardOptions = KeyboardOptions(
            keyboardType = typeOfKeyboard,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@Composable
fun HeadingTextComponent(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 39.sp,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ImageComponent(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
    )
}


@Composable
fun MyButton(labelVal: String, navController: NavHostController) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable {
                if (labelVal == "Submit") {
                    navController.navigate("ResetPassword")
                }
            }
        )
    }
}