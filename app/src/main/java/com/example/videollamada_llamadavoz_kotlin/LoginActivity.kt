package com.example.videollamada_llamadavoz_kotlin

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.videollamada_llamadavoz_kotlin.databinding.ActivityLoginBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            val myUserId = binding.userId.text.toString()
            if (myUserId.isNotEmpty()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userId",myUserId)
                startActivity(intent)

                setupZegoUIKit(myUserId)
            }else{
                Toast.makeText(application, "Ingresa un nombre de usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupZegoUIKit(userId:String){
        val application : Application  = application
        val appID : Long = 328023178
        val appSign : String = "9ef134d250bfa1c4d4a329bce8570b37a6bcab86faee0a97196c1cb4eee416ab"
        val userName :String = userId

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(application,appID,appSign,userId,userName,callInvitationConfig)
    }

    override fun onDestroy(){
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}