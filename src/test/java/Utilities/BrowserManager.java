package Utilities;

import com.microsoft.playwright.*;

public class BrowserManager {
    protected static Playwright playwright;
    protected static com.microsoft.playwright.Browser browser;
    protected static BrowserContext context;

    static public Browser launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
        //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
        return browser;
    }

    static public Page setupNewPage(Browser browser) {
        context = browser.newContext();
        Page page = context.newPage();
        return page;
    }
}
