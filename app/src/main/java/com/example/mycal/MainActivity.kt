package com.example.mycal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    var lastDot :Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResults)

        val btn0:Button = findViewById(R.id.btn0)
        btn0.setOnClickListener{appendValue("0", false)}

        val btn1:Button = findViewById(R.id.btn1)
        btn1.setOnClickListener{appendValue("1", false)}

        val btn2:Button = findViewById(R.id.btn2)
        btn2.setOnClickListener{appendValue("2", false)}

        val btn3:Button = findViewById(R.id.btn3)
        btn3.setOnClickListener{appendValue("3", false)}

        val btn4:Button = findViewById(R.id.btn4)
        btn4.setOnClickListener{appendValue("4", false)}

        val btn5:Button = findViewById(R.id.btn5)
        btn5.setOnClickListener{appendValue("5", false)}

        val btn6:Button = findViewById(R.id.btn6)
        btn6.setOnClickListener{appendValue("6", false)}

        val btn7:Button = findViewById(R.id.btn7)
        btn7.setOnClickListener{appendValue("7", false)}

        val btn8:Button = findViewById(R.id.btn8)
        btn8.setOnClickListener{appendValue("8", false)}

        val btn9:Button = findViewById(R.id.btn9)
        btn9.setOnClickListener{appendValue("9", false)}

        val btnDec:Button = findViewById(R.id.btnDec)
        btnDec.setOnClickListener{appendValue(".", false)}

        val btnAdd:Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener{appendValue("+", false)}

        val btnSub:Button = findViewById(R.id.btnMin)
        btnSub.setOnClickListener{appendValue("-", false)}

        val btnMul:Button = findViewById(R.id.btnMul)
        btnMul.setOnClickListener{appendValue("*", false)}

        val btnDiv:Button = findViewById(R.id.btnDiv)
        btnDiv.setOnClickListener{appendValue("/", false)}

        val btnClr:Button = findViewById(R.id.btnClr)
        btnClr.setOnClickListener{appendValue("", true)}

        val btnBack:Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            val numExpression = tvResult.text.toString()

            if(numExpression.isNotEmpty()){
                tvResult.text = numExpression.substring(0,numExpression.length-1)
            }

        }

        val btnSign: Button = findViewById(R.id.btnSign)
        btnSign.setOnClickListener{
            var expression = tvResult.text.toString()
            try {
                var number = expression.toDouble()
                number *= -1
                expression = number.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
            tvResult.text = expression
        }

        val btnEqual :Button = findViewById(R.id.btnEql)
        btnEqual.setOnClickListener{
            try{
                val expression = ExpressionBuilder(tvResult.text.toString()).build()
                val result = expression.evaluate()
                val finalRes = result.toLong()
                if(result == finalRes.toDouble()){
                    Toast.makeText(this,"Double",Toast.LENGTH_SHORT).show()
                    tvResult.text = finalRes.toString()
                }else
                    tvResult.text = result.toString()
            }
            catch(e:Exception){
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }

    }

    private fun appendValue(s: String, clear: Boolean) {
        if (clear) {
            tvResult.text = ""
        } else {
            tvResult.append(s)
        }
    }

    fun onDecimalPoint(view: View)
    {
        if(!lastDot)
        {
            tvResult.append(".")
            lastDot=true
        }
    }

}