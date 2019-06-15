package com.fomalhaut.gold.Bean;

import java.util.List;

public class Gold {

    /**
     * status : 0
     * msg : ok
     * result : [{"type":"Au(T+D)","typename":"黄金延期","price":"304.53","openingprice":"304.90","maxprice":"305.45","minprice":"303.50","changepercent":"-0.20%","lastclosingprice":"305.14","tradeamount":"56668.0000","updatetime":"2019-06-14 23:59:14"},{"type":"mAu(T+D)","typename":"迷你黄金延期","price":"304.44","openingprice":"305.00","maxprice":"306.11","minprice":"303.61","changepercent":"-0.21%","lastclosingprice":"305.09","tradeamount":"49918.0000","updatetime":"2019-06-14 23:59:04"},{"type":"Au99.99","typename":"沪金99","price":"304.40","openingprice":"306.00","maxprice":"306.01","minprice":"303.60","changepercent":"-0.21%","lastclosingprice":"305.04","tradeamount":"14458.0000","updatetime":"2019-06-14 23:59:13"},{"type":"Au(T+N2)","typename":"延期双金","price":"307.20","openingprice":"317.00","maxprice":"317.00","minprice":"306.10","changepercent":"-0.03%","lastclosingprice":"307.30","tradeamount":"612.0000","updatetime":"2019-06-14 23:59:14"},{"type":"Au(T+N1)","typename":"延期单金","price":"311.50","openingprice":"306.60","maxprice":"313.00","minprice":"306.60","changepercent":"2.00%","lastclosingprice":"305.40","tradeamount":"272.0000","updatetime":"2019-06-14 23:59:14"},{"type":"iAu99.99","typename":"IAU99.99","price":"301.00","openingprice":"302.00","maxprice":"302.00","minprice":"300.80","changepercent":"-0.49%","lastclosingprice":"302.47","tradeamount":"120.0000","updatetime":"2019-06-14 23:59:03"},{"type":"Au100g","typename":"沪金100G","price":"304.00","openingprice":"305.25","maxprice":"305.25","minprice":"303.55","changepercent":"-0.11%","lastclosingprice":"304.33","tradeamount":"32.0000","updatetime":"2019-06-14 23:59:13"},{"type":"Au99.95","typename":"沪金95","price":"305.00","openingprice":"305.00","maxprice":"305.00","minprice":"305.00","changepercent":"-0.01%","lastclosingprice":"305.02","tradeamount":"6.0000","updatetime":"2019-06-14 23:59:13"},{"type":"iAu100g","typename":"IAU100G","price":"0.00","openingprice":"0.00","maxprice":"0.00","minprice":"0.00","changepercent":"0.00%","lastclosingprice":"280.00","tradeamount":"0.0000","updatetime":"2019-06-14 23:59:13"},{"type":"iAu99.5","typename":"IAU99.5","price":"0.00","openingprice":"0.00","maxprice":"0.00","minprice":"0.00","changepercent":"0.00%","lastclosingprice":"237.80","tradeamount":"0.0000","updatetime":"2019-06-14 23:59:03"},{"type":"Pt99.95","typename":"沪铂95","price":"0.00","openingprice":"0.00","maxprice":"0.00","minprice":"0.00","changepercent":"0.00%","lastclosingprice":"187.50","tradeamount":"0.0000","updatetime":"2019-06-14 23:59:03"}]
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
         * type : Au(T+D)
         * typename : 黄金延期
         * price : 304.53
         * openingprice : 304.90
         * maxprice : 305.45
         * minprice : 303.50
         * changepercent : -0.20%
         * lastclosingprice : 305.14
         * tradeamount : 56668.0000
         * updatetime : 2019-06-14 23:59:14
         */

        private String type;
        private String typename;
        private String price;
        private String openingprice;
        private String maxprice;
        private String minprice;
        private String changepercent;
        private String lastclosingprice;
        private String tradeamount;
        private String updatetime;

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

        public String getOpeningprice() {
            return openingprice;
        }

        public void setOpeningprice(String openingprice) {
            this.openingprice = openingprice;
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

        public String getChangepercent() {
            return changepercent;
        }

        public void setChangepercent(String changepercent) {
            this.changepercent = changepercent;
        }

        public String getLastclosingprice() {
            return lastclosingprice;
        }

        public void setLastclosingprice(String lastclosingprice) {
            this.lastclosingprice = lastclosingprice;
        }

        public String getTradeamount() {
            return tradeamount;
        }

        public void setTradeamount(String tradeamount) {
            this.tradeamount = tradeamount;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
