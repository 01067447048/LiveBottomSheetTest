package com.jaehyeon.livebottomsheettest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Jaehyeon on 2022/09/05.
 */
class MainBottomSheetViewModel: ViewModel() {

    private val _typeAButtonState = MutableLiveData(false)
    val typeAButtonState: LiveData<Boolean> get() = _typeAButtonState

    private val _typeBButtonState = MutableLiveData(false)
    val typeBButtonState: LiveData<Boolean> get() = _typeBButtonState

    private val _typeCButtonState = MutableLiveData(false)
    val typeCButtonState: LiveData<Boolean> get() = _typeCButtonState

    private val _dayTypeValue = MutableLiveData(DayType.Day15)
    val dayTypeValue: LiveData<DayType> get() = _dayTypeValue

    private val _lifeType = MutableLiveData(LifeType.None)
    val lifeType: LiveData<LifeType> get() = _lifeType

    fun setButtonState(type: Int) {
        val buttonA = _typeAButtonState.value ?: false
        val buttonB = _typeBButtonState.value ?: false
        val buttonC = _typeCButtonState.value ?: false

        when(type) {
            0 -> {
                _typeBButtonState.value = false
                _typeCButtonState.value = false
                _typeAButtonState.value = !buttonA
            }

            1 -> {
                _typeAButtonState.value = false
                _typeCButtonState.value = false
                _typeBButtonState.value = !buttonB
            }

            2 -> {
                _typeBButtonState.value = false
                _typeAButtonState.value = false
                _typeCButtonState.value = !buttonC
            }

            else -> Unit
        }

        validateLife()
    }

    fun setDayTypeValue(type: DayType) {
        _dayTypeValue.value = type
        validateLife()
    }

    private fun validateLife() {
        val buttonA = _typeAButtonState.value ?: false
        val buttonB = _typeBButtonState.value ?: false
        val buttonC = _typeCButtonState.value ?: false
        val dayType = _dayTypeValue.value ?: DayType.Error

        when {
            buttonA && dayType == DayType.Day15 -> _lifeType.value = LifeType.Easy15Days
            buttonA && dayType == DayType.Day30 -> _lifeType.value = LifeType.Easy30Days
            buttonA && dayType == DayType.Day50 -> _lifeType.value = LifeType.Easy50Days
            buttonB && dayType == DayType.Day15 -> _lifeType.value = LifeType.Normal15Days
            buttonB && dayType == DayType.Day30 -> _lifeType.value = LifeType.Normal30Days
            buttonB && dayType == DayType.Day50 -> _lifeType.value = LifeType.Normal50Days
            buttonC && dayType == DayType.Day15 -> _lifeType.value = LifeType.Hard15Days
            buttonC && dayType == DayType.Day30 -> _lifeType.value = LifeType.Hard30Days
            buttonC && dayType == DayType.Day50 -> _lifeType.value = LifeType.Hard50Days
            else -> _lifeType.value = LifeType.None
        }
    }

}