package com.fomalhaut.gold.Bean;

import java.util.List;

public class Gold {


    /**
     * code : 0
     * data : [{"ask":1381.35,"bid":1381.27,"contractSize":100,"ctm":1560722400,"digits":2,"symbol":"XAUUSD","todayHigh":1393.86,"todayLow":1357.73,"todayOpen":1360.29,"trade":2,"workingTime":true,"yesterdayClose":1360.34},{"ask":1511.09,"bid":1504.74,"contractSize":150,"ctm":1560722400,"digits":1,"symbol":"XPDUSD","todayHigh":1505.37,"todayLow":1500.63,"todayOpen":1500.9,"trade":2,"workingTime":true,"yesterdayClose":1498.16},{"ask":815.22,"bid":814.23,"contractSize":150,"ctm":1560722400,"digits":2,"symbol":"XPTUSD","todayHigh":815.08,"todayLow":810.64,"todayOpen":812.05,"trade":2,"workingTime":true,"yesterdayClose":810.93},{"ask":1385.8,"bid":1385.7,"contractSize":10,"ctm":1560722400,"digits":2,"symbol":"GOLD.comex","todayHigh":1396.9,"todayLow":1361.3,"todayOpen":1364.4,"trade":1,"workingTime":true,"yesterdayClose":1364.3},{"ask":1499.5,"bid":1498.3,"contractSize":10,"ctm":1560722400,"digits":2,"symbol":"PALL.comex","todayHigh":1499,"todayLow":1493.1,"todayOpen":1493.1,"trade":1,"workingTime":true,"yesterdayClose":1494.7},{"ask":814.5,"bid":814.2,"contractSize":10,"ctm":1560722400,"digits":2,"symbol":"PLAT.comex","todayHigh":814.8,"todayLow":810.3,"todayOpen":811.3,"trade":1,"workingTime":true,"yesterdayClose":811.1},{"ask":815.41,"bid":814.68,"contractSize":10,"ctm":1560722400,"digits":2,"symbol":"PMAP","todayHigh":815.27,"todayLow":809.16,"todayOpen":811.88,"trade":1,"workingTime":true,"yesterdayClose":812.06}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ask : 1381.35
         * bid : 1381.27
         * contractSize : 100
         * ctm : 1560722400
         * digits : 2
         * symbol : XAUUSD
         * todayHigh : 1393.86
         * todayLow : 1357.73
         * todayOpen : 1360.29
         * trade : 2
         * workingTime : true
         * yesterdayClose : 1360.34
         */

        private double ask;
        private double bid;
        private int contractSize;
        private int ctm;
        private int digits;
        private String symbol;
        private double todayHigh;
        private double todayLow;
        private double todayOpen;
        private int trade;
        private boolean workingTime;
        private double yesterdayClose;

        public double getAsk() {
            return ask;
        }

        public void setAsk(double ask) {
            this.ask = ask;
        }

        public double getBid() {
            return bid;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public int getContractSize() {
            return contractSize;
        }

        public void setContractSize(int contractSize) {
            this.contractSize = contractSize;
        }

        public int getCtm() {
            return ctm;
        }

        public void setCtm(int ctm) {
            this.ctm = ctm;
        }

        public int getDigits() {
            return digits;
        }

        public void setDigits(int digits) {
            this.digits = digits;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getTodayHigh() {
            return todayHigh;
        }

        public void setTodayHigh(double todayHigh) {
            this.todayHigh = todayHigh;
        }

        public double getTodayLow() {
            return todayLow;
        }

        public void setTodayLow(double todayLow) {
            this.todayLow = todayLow;
        }

        public double getTodayOpen() {
            return todayOpen;
        }

        public void setTodayOpen(double todayOpen) {
            this.todayOpen = todayOpen;
        }

        public int getTrade() {
            return trade;
        }

        public void setTrade(int trade) {
            this.trade = trade;
        }

        public boolean isWorkingTime() {
            return workingTime;
        }

        public void setWorkingTime(boolean workingTime) {
            this.workingTime = workingTime;
        }

        public double getYesterdayClose() {
            return yesterdayClose;
        }

        public void setYesterdayClose(double yesterdayClose) {
            this.yesterdayClose = yesterdayClose;
        }
    }
}
