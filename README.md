# UI Tests Project (Web + Mobile)

Автоматизированные UI‑тесты для:
- веб‑сайта `https://books.toscrape.com/` (Selenium + TestNG);
- Android‑приложения Wikipedia (Appium + TestNG).

Проект оформлен как учебный, но сценарии максимально приближены к реальным.

## Требования

- Java 11+
- Maven 3+
- Google Chrome
- Android SDK / эмулятор или реальное устройство
- Appium Server (1.x или 2.x, локально)

## Структура проекта

- `src/test/java/com/example/tests/web/pages` — Page Object’ы для `books.toscrape.com`.
- `src/test/java/com/example/tests/web/tests` — веб‑тесты (TestNG, группы `web`, `web-legacy`).
- `src/test/java/com/example/tests/mobile/pages` — Page Object’ы / экраны для Wikipedia.
- `src/test/java/com/example/tests/mobile/tests` — мобильные тесты (TestNG, группа `mobile`).
- `src/test/java/com/example/tests/common` — общие утилиты (`Config`, фабрики драйверов и т. п.).
- `src/test/resources/config.properties` — конфиг (URL сайта, настройки Appium и устройства).

## Веб‑тесты (Selenium + TestNG)

Используется сайт `https://books.toscrape.com/`. Для него реализованы Page Object’ы (`HomePage`, `CategoryPage`, `BookPage`, `MainPage`, `ProductPage`) и набор тестов.

**Основные сценарии (группа `web`):**

- `travelCategoryHeaderShouldBeCorrect` — переход в категорию *Travel* и проверка заголовка.
- `firstBookDetailsShouldBeDisplayed` — открытие первой книги в *Travel*, проверка названия, цены, наличия текста о доступности и кнопки **Add to basket**.
- `bookTitleShouldMatchBetweenListAndDetails` — сравнение названия книги в списке и на странице деталей.
- `paginationShouldChangeBooksList` — проверка, что при переходе на следующую страницу списка книг на главной странице меняется набор заголовков.

**Дополнительные сценарии (группа `web-legacy`):**

- `NavigationTest` — переход в категорию по имени и проверка заголовка.
- `ProductDetailsTest` — проверки деталей товара при переходе из категории и с главной.
- `PaginationTest` — проверка пагинации в категории *Mystery* (там есть вторая страница).

Все веб‑тесты используют ожидания (`WebDriverWait + ExpectedConditions`) и Page Object Model.

### Запуск веб‑тестов

Все тесты проекта:

```bash
mvn test
```

Только основные веб‑тесты:

```bash
mvn -Dgroups=web test
```

Legacy‑сценарии для веба:

```bash
mvn -Dgroups=web-legacy test
```

## Мобильные тесты (Wikipedia + Appium + TestNG)

Мобильная часть использует Appium Java Client (UiAutomator2) и проверяет Android‑приложение Wikipedia.

Параметры берутся из `src/test/resources/config.properties`:

- `mobile.platformName` — обычно `Android`;
- `mobile.deviceName` — имя устройства/эмулятора (`adb devices`);
- `mobile.automationName` — `UiAutomator2`;
- `mobile.appPackage` — `org.wikipedia`;
- `mobile.appActivity` — `org.wikipedia.main.MainActivity`;
- `mobile.appiumServerUrl` — URL Appium Server (например, `http://127.0.0.1:4723/` или с `/wd/hub` для Appium 1.x).

На устройстве должно быть установлено официальное приложение Wikipedia.

**Сценарии (группа `mobile`):**

- `SearchArticleTest`  
  Открытие приложения, пропуск онбординга, переход в поиск, ввод запроса *Selenium*, открытие первой статьи и проверка, что заголовок не пустой.

- `ArticleScrollTest`  
  Аналогичный поиск статьи *Selenium*, запоминание заголовка, скролл страницы вниз с помощью `mobile: scrollGesture`, проверка, что заголовок не изменился.

- `ThemeDialogTest`  
  Открытие статьи, нажатие кнопки **Theme** в нижнем тулбаре (`page_theme`), ожидание появления диалога с настройками темы и проверка наличия переключателя `theme_chooser_match_system_theme_switch`.

Экран онбординга (“Skip”) и попап “Introducing Wikipedia games” закрываются автоматически в соответствующих Page Object’ах.

### Запуск мобильных тестов

1. Убедитесь, что:
   - настроен Android SDK, переменные `ANDROID_HOME` / `ANDROID_SDK_ROOT` заданы;
   - `adb devices` показывает ваше устройство/эмулятор в статусе `device`;
   - установлен и запущен Appium Server по адресу из `config.properties`;
   - на устройстве установлено приложение Wikipedia с пакетом `org.wikipedia`.

2. Запустите мобильные тесты:

```bash
mvn -Dgroups=mobile test
```

## Технические детали

- Язык: Java 11
- Сборка: Maven
- Веб: Selenium WebDriver + TestNG + WebDriverManager
- Мобайл: Appium Java Client + TestNG
- Паттерн: Page Object Model для веб‑и мобильных экранов

`Config` читает свойства из `config.properties`, `WebDriverFactory` и `MobileDriverFactory` создают необходимые драйверы.

## Кратко о запуске

- Все тесты: `mvn test`
- Только веб: `mvn -Dgroups=web test`
- Только мобильные: `mvn -Dgroups=mobile test`
- Дополнительные веб‑сценарии: `mvn -Dgroups=web-legacy test`

README описывает минимальное окружение и команды запуска; деталей по установке Android SDK/Appium достаточно, чтобы воспроизвести проект на другом рабочем месте.
