package com.dmm.bootcamp.yatter2023.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

@Composable
fun LoginTemplate(
    userName: String,
    onChangedUserName: (String) -> Unit,
    password: String,
    onChangedPassword: (String) -> Unit,
    isEnableLogin: Boolean,
    isLoading: Boolean,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit,
){

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ログイン")}) 
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = "ユーザー名"
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = userName,
                    onValueChange = onChangedUserName,
                    placeholder = {
                        Text(text = "username")
                    },
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "パスワード"
                )
                OutlinedTextField(singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = password,
                    onValueChange = onChangedPassword,
                    placeholder = {
                        Text(text = "password")
                    },
                )

                Button(
                    enabled = isEnableLogin,
                    onClick = onClickLogin,
                    modifier = Modifier.fillMaxWidth()
                    ) {
                    Text(text = "ログイン")
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                TextButton(
                    onClick = onClickRegister,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "新規会員登録")
                }

            }

            if (isLoading){
                CircularProgressIndicator()
            }

        }

    }
}


@Preview
@Composable
fun LoginTemplatePreview() {
    Yatter2023Theme {
        Surface() {
            LoginTemplate(
                userName = "usernamea",
                onChangedUserName = {},
                password = "password",
                onChangedPassword = {},
                isEnableLogin = true,
                isLoading = true,
                onClickLogin = {},
                onClickRegister = {},)
        }
    }
}