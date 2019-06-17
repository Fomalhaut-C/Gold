package com.fomalhaut.gold.Bean;

import java.util.List;

public class Forward {

    /**
     * status : 0
     * msg : ok
     * result : [{"type":"AU1511","typename":"黄金1511","price":"237.25","changequantity":"1.951","buyprice":"233.25","buyamount":"0","sellprice":"244.6","sellamount":"0","tradeamount":"20","openingprice":"236.049","lastclosingprice":"235.299","maxprice":"237.25","minprice":"236.049","holdamount":"16","increaseamount":"0"},{"type":"AU1512","typename":"黄金1512","price":"238.7","changequantity":"-0.949","buyprice":"238.7","buyamount":"5","sellprice":"238.75","sellamount":"100","tradeamount":"210274","openingprice":"241.5","lastclosingprice":"239.649","maxprice":"241.5","minprice":"237.799","holdamount":"188302","increaseamount":"-6086"}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * type : AU1511
         * typename : 黄金1511
         * price : 237.25
         * changequantity : 1.951
         * buyprice : 233.25
         * buyamount : 0
         * sellprice : 244.6
         * sellamount : 0
         * tradeamount : 20
         * openingprice : 236.049
         * lastclosingprice : 235.299
         * maxprice : 237.25
         * minprice : 236.049
         * holdamount : 16
         * increaseamount : 0
         */

        private String type;
        private String typename;
        private String price;
        private String changequantity;
        private String buyprice;
        private String buyamount;
        private String sellprice;
        private String sellamount;
        private String tradeamount;
        private String openingprice;
        private String lastclosingprice;
        private String maxprice;
        private String minprice;
        private String holdamount;
        private String increaseamount;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getChangequantity() {
            return changequantity;
        }

        public void setChangequantity(String changequantity) {
            this.changequantity = changequantity;
        }

        public String getBuyprice() {
            return buyprice;
        }

        public void setBuyprice(String buyprice) {
            this.buyprice = buyprice;
        }

        public String getBuyamount() {
            return buyamount;
        }

        public void setBuyamount(String buyamount) {
            this.buyamount = buyamount;
        }

        public String getSellprice() {
            return sellprice;
        }

        public void setSellprice(String sellprice) {
            this.sellprice = sellprice;
        }

        public String getSellamount() {
            return sellamount;
        }

        public void setSellamount(String sellamount) {
            this.sellamount = sellamount;
        }

        public String getTradeamount() {
            return tradeamount;
        }

        public void setTradeamount(String tradeamount) {
            this.tradeamount = tradeamount;
        }

        public String getOpeningprice() {
            return openingprice;
        }

        public void setOpeningprice(String openingprice) {
            this.openingprice = openingprice;
        }

        public String getLastclosingprice() {
            return lastclosingprice;
        }

        public void setLastclosingprice(String lastclosingprice) {
            this.lastclosingprice = lastclosingprice;
        }

        public String getMaxprice() {
            return maxprice;
        }

        public void setMaxprice(String maxprice) {
            this.maxprice = maxprice;
        }

        public String getMinprice() {
            return minprice;
        }

        public void setMinprice(String minprice) {
            this.minprice = minprice;
        }

        public String getHoldamount() {
            return holdamount;
        }

        public void setHoldamount(String holdamount) {
            this.holdamount = holdamount;
        }

        public String getIncreaseamount() {
            return increaseamount;
        }

        public void setIncreaseamount(String increaseamount) {
            this.increaseamount = increaseamount;
        }
    }
}
