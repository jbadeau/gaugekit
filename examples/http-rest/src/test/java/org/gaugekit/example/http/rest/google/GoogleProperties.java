package org.gaugekit.example.http.rest.google;

import org.aeonbits.owner.Config;

@Config.Sources({"system:env"})
public interface GoogleProperties extends Config {

    String google_base_url();

}