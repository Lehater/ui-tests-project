# UI Tests Project (Web + Mobile)

Учебный проект с автотестами для:
- веб-сайта (Selenium + TestNG);
- мобильного приложения Wikipedia (Appium + TestNG).

## Стек

- Java 11+
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- Appium Java Client

## Структура проекта

- `src/test/java/com/example/tests/web/pages` — веб Page Object'ы для сайта `books.toscrape.com`
- `src/test/java/com/example/tests/web/tests` — веб-тесты (TestNG, группа `web`)
- `src/test/java/com/example/tests/mobile/pages` — мобильные Page Object'ы для Wikipedia
- `src/test/java/com/example/tests/mobile/tests` — мобильные тесты (TestNG, группа `mobile`)
- `src/test/java/com/example/tests/common` — общие драйверы/конфигурация
- `src/test/resources` — конфигурация (`config.properties`)

## Статус

- [x] Базовая структура Maven-проекта
- [x] Фабрики драйверов для веб и мобильных тестов
- [x] Page Object'ы для сайта
- [x] Page Object'ы для Wikipedia
- [x] Набор веб-сценариев
- [x] Набор стабильных мобильных сценариев
- [x] README с инструкциями по запуску


## Запуск тестов

### Веб-тесты (Selenium)

Требования:
- Установлен JDK 11+
- Доступен интернет (для открытия https://books.toscrape.com/)
- Google Chrome установлен локально

Команда запуска всех тестов (включая веб и мобайл):

```bash
mvn test
```

Для запуска только веб-тестов можно использовать:

- запуск по группе TestNG:

```bash
mvn -Dgroups=web test
```

- запуск классов/методов из пакета `com.example.tests.web.tests` через конфигурацию TestNG в IDE.

### Мобильные тесты (Appium + Wikipedia)

Требования:
- Установлен JDK 11+
- Установлен Android SDK / Android Studio
- Создан и запущен Android-эмулятор (например, Pixel 3a, Android 11)
- Установлен Appium Server (например, Appium Desktop или appium через npm)
- На эмулятор установлено приложение Wikipedia (org.wikipedia)

Базовые шаги:
1. Запустить Android-эмулятор.
2. Убедиться, что устройство видно через adb:

   ```bash
   adb devices
   ```

3. Запустить Appium Server на `http://127.0.0.1:4723/` (порт и URL должны совпадать с параметром `mobile.appiumServerUrl` в `config.properties`, по умолчанию — `http://127.0.0.1:4723/`).
4. Убедиться, что настройки в `src/test/resources/config.properties` соответствуют вашему устройству/эмулятору (имя девайса, версия Android при необходимости).
5. Запустить тесты (из IDE или командой `mvn test`).

Основные стабильные мобильные сценарии реализованы в:
- `com.example.tests.mobile.tests.SearchArticleTest` — поиск статьи и проверка заголовка.
- `com.example.tests.mobile.tests.ArticleScrollTest` — открытие статьи и прокрутка вниз.

Тест `com.example.tests.mobile.tests.LanguageChangeTest` помечен как `enabled = false`, так как смена языка зависит от конкретной версии
приложения и может потребовать ручной настройки локаторов и шагов.


## Реализованные сценарии

### Веб (https://books.toscrape.com/)

1. Навигация в категорию `Travel` и проверка заголовка.
2. Открытие первой книги в категории `Travel` и проверка:
   - заголовка книги;
   - отображения цены;
   - отображения блока наличия;
   - наличия кнопки `Add to basket`.
3. Открытие первой книги с главной страницы и проверка консистентности заголовка.
4. Переход на следующую страницу категории (пагинация) и проверка смены списка книг.

Все веб-тесты помечены группой `web`.

### Мобильные сценарии (Wikipedia)

1. Поиск статьи по ключевому слову `Selenium` и проверка, что заголовок открытой статьи содержит `Selenium`.
2. Поиск статьи по слову `Appium` и проверка, что заголовок статьи не пустой.
3. Прокрутка статьи `Selenium` до секции `References` и проверка наличия этого блока (через UiScrollable).

Все мобильные тесты помечены группой `mobile`.
Тест `LanguageChangeTest` отключён (`enabled = false`) и служит заготовкой для сценария смены языка.

## Примеры запуска по группам TestNG

Запуск только веб-тестов:

```bash
mvn -Dgroups=web test
```

Запуск только мобильных тестов:

```bash
mvn -Dgroups=mobile test
```
