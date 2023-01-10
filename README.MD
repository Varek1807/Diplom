Дипломный проект по профессии «Тестировщик»
---
## Необходимое окружение: ##

_1. IntelliJ IDEA;_

_2. Docker;_

_3. Порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных)_

## Инструкции по запуску автотестов ##

1. Скачайте архив
2. Запустите Docker
3. Откройте проект в IntelliJ IDEA
4. В терминале в папке проекта запустить контейнеры: docker-compose up
5. В терминале открыть новую сессию, в папке проекта запустить SUT:
   * Для _MySQL_ - командой java -jar aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
   * Для _PostgreSQL_ - командой java -jar aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app
6. В терминале открыть новую сессию, в папке проекта запустить тесты:
   * Для _MySQL_ - командой ./gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/app
   * Для _PostgreSQL_ - командой ./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/app
7. Дождаться завершения автотестов
8. Для просмотра отчета Allure в терминале ввести команду: ./gradlew allureServe

*Остановка процессов производится в соответствующих терминалах следующими командами:*
   * Allure - Ctrl+C
   * SUT - Ctrl+C
   * Контейнеры - docker-compose down

## Документация ##
1. [План автоматизации](docs/Plan.md)
2. [Отчётные документы по итогам тестирования](docs/Report.md)
3. [Отчётные документы по итогам автоматизации](docs/Summary.md)