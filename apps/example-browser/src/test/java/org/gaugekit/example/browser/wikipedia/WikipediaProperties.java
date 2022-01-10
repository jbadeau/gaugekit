package org.gaugekit.example.browser.wikipedia;

import org.aeonbits.owner.Config;

@Config.Sources({"system:env"})
public interface WikipediaProperties extends Config {

    String wikipedia_base_url();

}