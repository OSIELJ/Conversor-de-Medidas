package com.osiel.conversordemedidas.models.strategies

class MetersToKilometerStrategy : CalculationStrategy {


    override fun calculate(value: Double): Double = value / 1_000

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "Quilômetros" else "Quilômetro"


}