package com.example.thebarbershop.views.profileActivity

import com.example.thebarbershop.models.ProfileOption

class ProfileOptionsProvider {
    fun getProfileOptions(): List<ProfileOption> {
        return listOf(
            ProfileOption("ic_person", "Os Meus Dados", "Altere as informações do seu perfil"),
            ProfileOption("ic_key", "Os Meus Acessos", "Vizualize e altere os métodos de inicio de sessão da sua conta"),
            ProfileOption("ic_map", "Endereços", "Altere o endereço"),
            ProfileOption("ic_heart_fill", "Favoritos", "Os meus favoritos"),
            ProfileOption("ic_credit_card", "Os meus cartões", "Gira os seus cartões"),
            ProfileOption("ic_person", "Subscrições", "Acompanhe as suas subscrições"),//mudar o icon depois
            ProfileOption("ic_pacotes", "Os meus pacotes", "Acompanhe os seus pacotes"),
            ProfileOption("ic_historic", "Histórico", "Veja o histórico de marcações."),
            ProfileOption("ic_expenses", "Despesas", "Acompanhe as suas despesas."),
            ProfileOption("ic_lock", "Segurança", "Veja as suas opções de segurança"),
            // Add more ProfileOption objects as needed
        )
    }

}