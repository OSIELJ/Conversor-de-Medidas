package com.osiel.conversordemedidas.models.strategies

class KilometerToMeterStrategy : CalculationStrategy {


    override fun calculate(value: Double): Double = value * 1_000


    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "metros" else "metro"

}