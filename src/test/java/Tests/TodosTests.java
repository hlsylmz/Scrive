package Tests;

import Pages.TodosPage;
import Utilities.BrowserManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodosTests {
    static Browser browser;
    static TodosPage todosPage;
    static Page page;
    String url="https://todo.scriveqa.com/";

    private TodosTests() {
    }

    @BeforeAll
    @Order(0)
    static void launch(){
        browser = BrowserManager.launchBrowser();
        page=BrowserManager.setupNewPage(browser);
        todosPage=new TodosPage(page);
    }

    @Test
    @Order(1)
    @DisplayName("Navigate to Todo page")
    void navigateToTodosPage() {
        todosPage.navigateToPage(url);

        assertEquals(page.url(), url);
    }

    @Test
    @Order(2)
    @DisplayName("Add new item and verify item has been added")
    void addItem1(){
        todosPage.addItem("Item 1");

        assertThat(todosPage.getAddItem1()).isVisible();
    }

    @Test
    @Order(3)
    @DisplayName("Add new item and verify item has been added")
    void addItem2(){
        todosPage.addItem("Item 2");

        assertThat(todosPage.getAddItem2()).isVisible();
    }

    @Test
    @Order(4)
    @DisplayName("Add new item and verify item has been added")
    void addItem3(){

        todosPage.addItem("Item 3");

        assertThat(todosPage.getAddItem3()).isVisible();
    }

    @Test
    @Order(5)
    @DisplayName("search an item")
    void searchItem(){
        todosPage.searchItem("Item 2");

        assertThat(todosPage.getSearchedItem()).isVisible();
    }

    @Test
    @Order(6)
    @DisplayName("search an item that does not exit")
    void searchItemNegativeTest(){
        todosPage.searchItem("Item 4");
        assertThat(todosPage.getAlert_info()).isVisible();
    }

    @Test
    @Order(7)
    @DisplayName("filter all items")
    void filterAllItems(){
        page.reload();
        todosPage.filterAllItems();

        assertThat(todosPage.getAddItem1()).isVisible();
        assertThat(todosPage.getAddItem2()).isVisible();
        assertThat(todosPage.getAddItem3()).isVisible();
    }

    @Test
    @Order(8)
    @DisplayName("filter active items")
    void filterActive_Items(){
        todosPage.filterCompletedItems();
        todosPage.filterActiveItems();

        assertThat(todosPage.getAddItem1()).isVisible();
        assertThat(todosPage.getAddItem2()).isVisible();
        assertThat(todosPage.getAddItem3()).isVisible();
    }

    @Test
    @Order(9)
    @DisplayName("click 'completed' button to filter when there is not completed items" +
            " and see alert-info")
    void filterCompleted_Items(){
        todosPage.filterCompletedItems();

        assertThat(todosPage.getAlert_info()).isVisible();
    }

    @Test
    @Order(10)
    @DisplayName("take an item to 'completed' category from 'active' category")
    void takeItemToCompletedTest(){
        todosPage.takeItemToCompleted();

        assertThat(todosPage.getAddItem1()).isVisible();
    }

    @Test
    @Order(11)
    @DisplayName("filter all again after taking an item " +
            "to 'completed' category from 'active' category")
    void filterAllAgain(){
        todosPage.filterAllItems();

        assertThat(todosPage.getAddItem1()).isVisible();
        assertThat(todosPage.getAddItem2()).isVisible();
        assertThat(todosPage.getAddItem3()).isVisible();
    }

    @Test
    @Order(12)
    @DisplayName("filter active again after taking an item " +
            "to 'completed' category from 'active' category")
    void filterActiveAgain(){
        todosPage.filterActiveItems();

        assertThat(todosPage.getActiveItem2()).isVisible();
        assertThat(todosPage.getActiveItem3()).isVisible();
    }


    @Test
    @Order(13)
    @DisplayName("clear completed item")
    void clear_CompletedItem(){
        todosPage.clearCompletedItem();

        assertThat(todosPage.getAlert_info()).isVisible();
    }

    @Test
    @Order(14)
    @DisplayName("refresh page after clean completed")
    void refreshPageAfterClearCompleted(){
        System.out.println(todosPage.getTotalItem().textContent());
        page.reload();
        System.out.println(todosPage.getTotalItem().textContent());

        assertEquals("2 items", todosPage.getTotalItem().textContent());
    }

    @Test
    @Order(15)
    @DisplayName("click Todos")
    void clickTodosTest(){
        System.out.println(todosPage.getTotalItem().textContent());
        todosPage.clickTodos();

        assertEquals("3 items", todosPage.getTotalItem().textContent());
    }

    @Test
    @Order(16)
    @DisplayName("press 'Esc' ")
    void press_Esc(){
        System.out.println(todosPage.getInfo().textContent());
        todosPage.pressEsc();
        System.out.println(todosPage.getInfo().textContent());

        assertEquals("Press `/` to search and `N` to create a new item.",
                todosPage.getInfo().textContent());
    }

    @Test
    @Order(17)
    @DisplayName("press 'Forwards slash' to search ")
    void press_ForwardSlash(){
        System.out.println(todosPage.getInfo().textContent());
        todosPage.pressForwardSlash();
        System.out.println(todosPage.getInfo().textContent());

        assertEquals("Press `Esc` to cancel.",
                todosPage.getInfo().textContent() );
        assertThat(todosPage.getSearchItemTextBox()).isVisible();
    }

    @Test
    @Order(18)
    @DisplayName("press 'N' to add item ")
    void pressN(){
        todosPage.press_N();

        assertThat(todosPage.getAddNewTextBox()).isVisible();
    }



    @AfterAll
    static void closeBrowser(){
        browser.close();
    }
}

