package com.osiel.conversordemedidas.models

import com.osiel.conversordemedidas.models.strategies.CalculationStrategy

data class CalculationStrategyHolder(
    val name: String,
    val calculationStrategy: CalculationStrategy
)