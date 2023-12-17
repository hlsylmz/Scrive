package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TodosPage {
    private final Page page;
    Locator addNewTextBox;
    Locator addItem1;
    Locator addItem2;
    Locator addItem3;
    Locator addSecondItem2;
    Locator searchItemLink;
    Locator addItemLink;
    Locator searchItemTextBox;
    Locator searchedItem;
    Locator all;
    Locator completed;
    Locator active;
    Locator clear_completed;
    Locator alert_info;
    Locator checkboxForItem_1;
    Locator checkboxForItem_2;
    Locator checkboxForItem_3;
    Locator totalItem;
    Locator todos;
    Locator info;
    Locator activeItem2;
    Locator activeItem3;
    Locator info_ForPressForwardSlashAndN;
    Locator info_forPressEsc;
    public TodosPage(Page page) {
        this.page = page;
        this.addNewTextBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Add new"));
        this.addItem1 = page.locator(".list-unstyled li.todo-item:first-child") ;
        this.addItem2 = page.locator(".list-unstyled li.todo-item:nth-child(2)");
        this.addItem3 = page.locator(".list-unstyled li.todo-item:nth-child(3)");
        this.addSecondItem2 = page.locator(".list-unstyled li.todo-item:nth-child(4)");
        this.searchItemLink=page.locator("//div[@class='pull-left buttons']//a[@title='Search']");
        this.addItemLink=page.locator("//div[@class='pull-left buttons']//a[@title='Add New']");
        this.searchItemTextBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"));
        this.searchedItem = page.locator(".list-unstyled:first-child");
        this.all= page.locator(".filters li:nth-child(1) a");
        this.completed=page.locator(".filters li:nth-child(3) a");
        this.active=page.locator(".filters li:nth-child(2) a");
        this.clear_completed=page.locator(".filters li:nth-child(4) a");
        this.alert_info=page.locator(".alert-info");
        this.checkboxForItem_1=page.locator(".list-unstyled li.todo-item:nth-child(1) input[type='checkbox']");
        this.checkboxForItem_2=page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Item 2"));
        this.checkboxForItem_3=page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Item 3"));
        this.totalItem=page.locator(".clearfix .pull-left:nth-child(2)");
        this.todos=page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Todos"));
        this.info=page.locator("p.info");
        this.activeItem2=page.locator("//label[contains(., 'Item 2')]");
        this.activeItem3=page.locator("//label[contains(., 'Item 3')]");
        this.info_ForPressForwardSlashAndN=page.locator("//p[contains(., 'Press `/` to search and `N` to create a new item.')]");
        this.info_forPressEsc=page.locator("//p[contains(., 'Press `Esc` to cancel.')]");
    }

    public void navigateToPage(String url) {
        page.navigate(url);
    }
    public void addItem(String itemName) {
        addNewTextBox.click();
        addNewTextBox.fill(itemName);
        page.keyboard().press("Enter");
    }

    public void searchItem(String itemName){
        searchItemLink.click();
        searchItemTextBox.clear();
        searchItemTextBox.fill(itemName);
        searchItemLink.click();
    }

    public void filterAllItems(){
        all.click();
    }

    public void filterCompletedItems(){
        completed.click();
    }
    public void filterActiveItems(){
        active.click();
    }

    public void takeItemToCompleted(){
        active.click();
        checkboxForItem_1.click();
        completed.click();
    }

    public void clearCompletedItem(){
        all.click();
        clear_completed.click();
        completed.click();
    }

    public void pressEsc(){
        page.keyboard().press("Escape");
    }

    public void pressForwardSlash() {
        if(info_ForPressForwardSlashAndN.isVisible()){
            page.keyboard().press("Slash");
        }
    }

    public void press_N() {
        pressEsc();
        page.keyboard().press("N");
    }

    public void clickTodos() {
        todos.click();
    }

    public Locator getAddItem1() {
        return addItem1;
    }

    public Locator getAddItem2() {
        return addItem2;
    }

    public Locator getAddItem3() {
        return addItem3;
    }

    public Locator getSearchedItem() {
        return searchedItem;
    }

    public Locator getAlert_info() {
        return alert_info;
    }

    public Locator getSearchItemTextBox() {
        return searchItemTextBox;
    }

    public Locator getAddNewTextBox() {
        return addNewTextBox;
    }

    public Locator getTotalItem() {
        return totalItem;
    }

    public Locator getInfo() {
        return info;
    }

    public Locator getActiveItem2() {
        return activeItem2;
    }

    public Locator getActiveItem3() {
        return activeItem3;
    }
}
