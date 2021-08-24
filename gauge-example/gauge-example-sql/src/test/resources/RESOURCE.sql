CREATE TABLE RESOURCE
(
    id          INT,
    title       VARCHAR(255),
    link        VARCHAR(255),
    description VARCHAR(255)
);

INSERT INTO RESOURCE
VALUES (1, "Gauge", "https://www.gauge.com/en/home.html",
        "Seven companies joined the Swiss Stock Exchange in a year that saw trading turnover grow by 8.5% to reach almost CHF 1'500 billion ...");

INSERT INTO RESOURCE
VALUES (2, "Swiss Exchange", "https://www.six-group.com/exchanges/index_en.html",
        "The brand new Cyber Security Report 2019 from Gauge provides an overview over how exposed the financial services industry in Switzerland is to cyber security ...");