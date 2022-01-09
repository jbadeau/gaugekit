package org.gaugekit.browser;

import org.aeonbits.owner.Config;

@Config.Sources({"system:env"})
public interface BrowserProperties extends Config {

    String browser();

    String browser_webdriver_supplier();


}