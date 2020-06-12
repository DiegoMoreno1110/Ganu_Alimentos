package com.dmr.ganu_alimentos
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import com.github.bassaer.chatmessageview.view.ChatView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.fragment_chat.view.*

class ChatFragment: Fragment() {
    private lateinit var mChatView: ChatView
    private var i = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_chat, container, false)
        FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer $ACCESS_TOKEN")
        mChatView = view.chatView as ChatView

        val human = ChatUser(1,"You", BitmapFactory.decodeResource(resources,R.drawable.ic_account_circle))
        val agent = ChatUser(2,"GanuBot",BitmapFactory.decodeResource(resources,R.drawable.ganubot))
        if (i == 1){
            requestDialog("Hola",agent)
            i++
        }
        mChatView.setOnClickSendButtonListener(
            View.OnClickListener {
                mChatView.send(Message.Builder().setRight(true).setUser(human).setText(mChatView.inputText).build())
                requestDialog(mChatView.inputText,agent)
                mChatView.inputText = ""
            }
        )
        return view
    }
    companion object {
        private const val ACCESS_TOKEN = "f0ee71e7093c4a32af7bd5a2870279c7"
    }

    private fun requestDialog(text:String, agent:ChatUser) {
        var handler = text
        if (handler == ""){
            handler = "Disculpa"
        }
        Fuel.get("https://api.dialogflow.com/v1/query?v=20170712&lang=es&query=$handler&sessionId=12345")
            .responseJson { _, _, result ->
                val reply = result.get().obj().getJSONObject("result")
                    .getJSONObject("fulfillment").getString("speech")
                mChatView.send(Message.Builder().setUser(agent).setText(reply).build())
            }
    }


}