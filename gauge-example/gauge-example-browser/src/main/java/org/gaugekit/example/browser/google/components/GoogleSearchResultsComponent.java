package org.gaugekit.example.browser.google.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GoogleSearchResultsComponent extends Component {

    public SelenideElement getSearchResult(int index) {
       return this.getSelf().findAll(By.className("g")).get(index);
    }

}