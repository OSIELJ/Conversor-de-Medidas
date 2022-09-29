package com.osiel.conversordemedidas.models.strategies

class MetersToCentimetersStrategy : CalculationStrategy {


    override fun calculate(value: Double): Double = value * 100

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "Centimetros" else "Centímetros"
}