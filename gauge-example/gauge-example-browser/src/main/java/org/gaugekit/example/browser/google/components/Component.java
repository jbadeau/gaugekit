package org.gaugekit.example.browser.google.components;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;

import java.util.HashSet;
import java.util.Set;

public class Component extends ElementsContainer {

    public Set<By> getIgnoredSelectors() {
        return new HashSet<By>();
    }
}
