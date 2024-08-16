package com.immortalidiot.rutlead.util

object Messages {
    const val SUCCESSFUL_REGISTRATION = "Успешная регистрация"
    const val SUCCESSFUL_LOGIN = "Успешный вход"
    const val UNKNOWN_ERROR = "Неизвестная ошибка, попробуйте позднее"
    const val NETWORK_ERROR = "Ошибка сети. Проверьте подключение к Интернету"
    const val EMPTY_STUDENT_ID = "Поле \"Номер студенческого билета\" не должно быть пустым"
    const val ONLY_DIGITS_IN_STUDENT_ID =
        "Поле \"Номер студенческого билета\" должно содержать только цифры"
    const val INCORRECT_LENGTH_STUDENT_ID = "Номер студенческого билета состоит из 8 цифр"
    const val EMPTY_PASSWORD = "Поле \"Пароль\" не должно быть пустым"
    const val INCORRECT_LENGTH_PASSWORD = "Пароль должен содержать минимум 8 символов"
    const val NOT_MATCHING_PASSWORDS = "Пароли не совпадают"
    const val EMPTY_EMAIL = "Поле \"Email \" не должно быть пустым"
    const val NOT_EMAIL = "Ввод не соответствует Email'у"
    const val INCORRECT_GROUP_FORMAT = "Неверный формат группы. Пример: УВП-111"
    const val INCORRECT_FULL_NAME = "Введите верные ФИО. Пример: Иванов Иван Иванович"
}