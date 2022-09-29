package com.osiel.conversordemedidas.models.strategies

class KilometerToCentimeters : CalculationStrategy {


    override fun calculate(value: Double): Double = value * 100_000


    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "Centímetros" else "Centímetros"

}