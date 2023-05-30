package test;

import com.codeborne.selenide.Condition;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRunner {

        @Test
        @DisplayName("Проверка поиска на сайте Wikipedia")
        public void test1SearchOnWikipedia() {
            open("https://www.wikipedia.org/");

            // Выполнение поиска и проверка результатов
            $("#searchInput").setValue("Роналдо").pressEnter();
            $("#firstHeading").shouldHave(text("Роналдо"));
        }

        @Test
        @DisplayName("Проверка наличия рабочей ссылки на странице 'Просмотр кода'")
        public void test2CodeViewPageLink() {
            open("https://www.wikipedia.org/wiki/Special:Code");

            $(".mw-ui-button", 1).shouldHave(text("Заглавная страница"));
        }

        @Test
        @DisplayName("Статья состоит из параграфов")
        public void test3ArticleParagraphs() {
            open("https://www.wikipedia.org/");

            // Открытие браузера и переход на страницу статьи
            open("https://www.wikipedia.org/wiki/Selenide");

            // Проверка, что статья состоит из параграфов
            $$(".mw-parser-output p").shouldHave(sizeGreaterThan(0));
        }

        @Test
        @DisplayName("Любая статья должна иметь содержание")
        public void test4ArticleContent() {
            open("https://www.wikipedia.org/");

            // Открытие браузера и переход на страницу статьи (пример ссылки)
            open("https://www.wikipedia.org/wiki/Selenide");

            // Проверка, что статья имеет содержание
            $("#toc").shouldBe(Condition.visible);

        }

        @Test
        @DisplayName("Смена языка на странице")
        public void test5LanguageChange() {
            open("https://www.wikipedia.org/");

            // Смена языка на странице
            $(".interlanguage-link-target[hreflang='de']").click();

            // Проверка, что язык успешно изменен
            $("#js-reel-load-base-url").shouldHave(text("https://de.wikipedia.org/"));
    }
}
