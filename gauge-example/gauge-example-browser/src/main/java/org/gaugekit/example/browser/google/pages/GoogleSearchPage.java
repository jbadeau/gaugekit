package org.gaugekit.example.browser.google.pages;

import org.gaugekit.example.browser.google.components.GoogleSearchComponent;
import org.gaugekit.example.browser.google.components.GoogleSearchResultsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class GoogleSearchPage extends Page {

    @FindBy(tagName = "form")
    public GoogleSearchComponent search;

    @FindBy(id = "search")
    public GoogleSearchResultsComponent searchResults;

    @Override
    public Set<By> getIgnoredSelectors() {
        Set<By> ignores = super.getIgnoredSelectors();
        ignores.addAll(search.getIgnoredSelectors());
        ignores.addAll(searchResults.getIgnoredSelectors());
        ignores.add(By.name("btnK"));
        return ignores;
    }

}
