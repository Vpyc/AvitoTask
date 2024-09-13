# Установка приложения
Чтобы установить приложение загрузите [apk-файл](https://github.com/Vpyc/AvitoTask/releases/download/v1.0.1/AvitoTask.apk). После загрузки, установите его на своё мобильное устройство.
Приложение лучше запускать при включённой тёмной теме телефона, т.к. приложение разрабатывалось в основном под тёмную тему телефона.

# Во время разработки столкнулся со следующими проблемами
1. Проблема с API. Если быть более точным, то не работает аутентификация при помощи токена. Для решения данной проблемы пытался использовать разные запросы к API, изучил документацию к данному API на gitHub, но это не помогло решить проблему.
2. Составление запроса для сортировки. При составлении запроса для сортировки в запросе присутствовал "+", который кодировался как "%2B". Изучил вопрос по данной теме и пришёл к решению добавить флаг "encode = true" для запроса в API.
