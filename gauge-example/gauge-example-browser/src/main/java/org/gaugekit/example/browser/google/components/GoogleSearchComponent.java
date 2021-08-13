package org.gaugekit.example.browser.google.components;

import org.openqa.selenium.By;

public class GoogleSearchComponent extends Component {

    public GoogleSearchComponent search(String query) {
        this.getSelf().find(By.tagName("input"))
                .setValue(query)
                .submit();
        return this;
    }

}