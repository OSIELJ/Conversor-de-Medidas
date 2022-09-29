package com.osiel.conversordemedidas

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.osiel.conversordemedidas.databinding.ActivityMainBinding
import com.osiel.conversordemedidas.models.CalculationStrategyHolder
import com.osiel.conversordemedidas.models.Calculator
import com.osiel.conversordemedidas.models.strategies.KilometerToCentimeters
import com.osiel.conversordemedidas.models.strategies.KilometerToMeterStrategy
import com.osiel.conversordemedidas.models.strategies.MetersToCentimetersStrategy
import com.osiel.conversordemedidas.models.strategies.MetersToKilometerStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    private val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Quilômetro para centímetros", KilometerToCentimeters()),
        CalculationStrategyHolder("Quilômetro para metros", KilometerToMeterStrategy()),
        CalculationStrategyHolder("Metros para centimetros", MetersToCentimetersStrategy()),
        CalculationStrategyHolder("Metros para quilômetros", MetersToKilometerStrategy())
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var value = 0.0
        var position = 0

        savedInstanceState?.let {
            value = it.getDouble("VALUE")
            position = it.getInt("POSITION")
        }


        setUi(value, position)

        setActions()


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try {
            outState.putDouble("VALUE", binding.edtValue.text.toString().toDouble())
        }catch (e: java.lang.NumberFormatException) {

        }
        outState.putInt("POSITION", binding.spConversions.selectedItemPosition)
    }


    private fun setActions() {


        binding.btnConvert.setOnClickListener {

            try {

                val value = binding.edtValue.text.toString().toDouble()
                val calculationStrategy = supportedCalculationStrategies[
                       binding.spConversions.selectedItemPosition
                ].calculationStrategy

                Calculator.setCalculationStrategy(
                    calculationStrategy
                )

               val result =  Calculator.calculate(value)
                val label = calculationStrategy.getResultLabel(result != 1.toDouble())

                showResult(result, label)


            } catch (e: NumberFormatException){
                binding.edtValue.error = "Valor inválido"
                    binding.edtValue.requestFocus()
            }

        }


            binding.btnClean.setOnClickListener {

            clean()

        }

    }

    private fun clean() {
        binding.edtValue.setText("")
            binding.edtValue.error = null

                binding.spConversions.setSelection(0)
    }

    private fun showResult(result: Double, label: String) {

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", result)
        intent.putExtra("LABEL", label)

        startActivity(intent)

    }

    private fun setUi(value: Double, position: Int) {

        binding.edtValue.setText(value.toString())

        val spAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spConversions.adapter = spAdapter
                binding.spConversions.setSelection(position)
    }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculationStrategies.forEach {
            spinnerData.add(it.name)
        }
        return spinnerData
    }
}