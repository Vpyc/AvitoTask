package com.example.avitotask.utils

/**
 * Интерфейс для получения ресурсов приложения
 */
interface ResourceProvider {
    /**
     * Используется для получения строки со значением
     * @param resId Id ресурса
     * @return Строка со значением
     */
    fun getStringById(resId: Int): String
}