package com.example.videollamada_llamadavoz_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.videollamada_llamadavoz_kotlin.databinding.ActivityMainBinding
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myUserId = intent.getStringExtra("userId")

        binding.tvNameUserId.text = "Hola $myUserId! \n ¿A quien deseas llamar?"

        binding.etNameUserId.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userName = binding.etNameUserId.text.toString()
                if (userName.isNotEmpty()){
                    startVideoLlamada(userName)
                    startLlamadaVoz(userName)
                }else{
                    Toast.makeText(application,"Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun startVideoLlamada(userId:String){
        val userName : String = userId

        binding.btnVideoLlamada.setIsVideoCall(true)
        binding.btnVideoLlamada.resourceID = "zego_uikit_call"
        binding.btnVideoLlamada.setInvitees(listOf(ZegoUIKitUser(userId,userName)))
    }

    private fun startLlamadaVoz(userId: String){

        val userName : String = userId

        binding.btnVideoLlamadaVoz.setIsVideoCall(false)
        binding.btnVideoLlamadaVoz.resourceID = "zego_uikit_call"
        binding.btnVideoLlamadaVoz.setInvitees(listOf(ZegoUIKitUser(userId,userName)))

    }


}