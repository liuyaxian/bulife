package com.ruiya.file.io.vo;

import java.math.BigDecimal;

public class FundInfo {
        private String fundid;
        private BigDecimal subamt;

        public String getFundid() {
            return fundid;
        }

        public void setFundid(String fundid) {
            this.fundid = fundid;
        }

        public BigDecimal getSubamt() {
            return subamt;
        }

        public void setSubamt(BigDecimal subamt) {
            this.subamt = subamt;
        }

}
