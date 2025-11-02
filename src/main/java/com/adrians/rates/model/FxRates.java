package com.adrians.rates.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "FxRates")
public class FxRates {

    @JacksonXmlProperty(localName = "FxRate")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FxRate> fxRates;

    public List<FxRate> getFxRates() {
        return fxRates;
    }

    public void setFxRates(List<FxRate> fxRates) {
        this.fxRates = fxRates;
    }

    public static class FxRate {
        @JacksonXmlProperty(localName = "CcyAmt")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CcyAmt> ccyAmts;

        @JacksonXmlProperty(localName = "Dt")
        private String date;

        @JacksonXmlProperty(localName = "Tp")
        private String type;

        public List<CcyAmt> getCcyAmts() {
            return ccyAmts;
        }

        public void setCcyAmts(List<CcyAmt> ccyAmts) {
            this.ccyAmts = ccyAmts;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class CcyAmt {
        @JacksonXmlProperty(localName = "Ccy")
        private String currency;

        @JacksonXmlProperty(localName = "Amt")
        private String amount;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}