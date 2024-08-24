package com.example.thebarbershop.views.profileActivity

import com.example.thebarbershop.models.ProfileOption

class ProfileOptionsProvider {
    fun getProfileOptions(): List<ProfileOption> {
        return listOf(
            ProfileOption("ic_person", "Os Meus Dados", "Altere as informações do seu perfil"),
            ProfileOption("ic_key", "Os Meus Acessos", "Vizualize e altere os métodos de inicio de sessão da sua conta"),
            ProfileOption("ic_map", "Endereços", "Altere o seu endereço"),
            // Add more ProfileOption objects as needed
        )
    }

}