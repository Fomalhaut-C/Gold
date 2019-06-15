package com.fomalhaut.gold.Bean;

import java.util.List;

public class Gold {

    /**
     * msg : success
     * result : [{"closePri":"299.64","highPic":"300.51","limit":"0.89%","lowPic":"296.08","name":"沪金9999","openPri":"298.00","time":"2019-06-12 15:30:03","totalTurnover":"369049952000.00","totalVol":"1233126.00","variety":"AU99.99","yesDayPic":"297.00"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"黄金995","openPri":"--","time":"2019-06-11 20:00:00","totalTurnover":"--","totalVol":"--","variety":"AU995","yesDayPic":"271.60"},{"closePri":"299.72","highPic":"300.53","limit":"0.85%","lowPic":"296.05","name":"黄金延期","openPri":"296.05","time":"2019-06-12 15:30:03","totalTurnover":"29626340000.00","totalVol":"99274.00","variety":"Au(T+D)","yesDayPic":"298.12"},{"closePri":"299.75","highPic":"300.70","limit":"0.79%","lowPic":"296.50","name":"延期单金","openPri":"296.50","time":"2019-06-12 15:29:24","totalTurnover":"3680344750.00","totalVol":"12276.00","variety":"Au(T+N1)","yesDayPic":"298.15"},{"closePri":"302.50","highPic":"303.30","limit":"1.14%","lowPic":"298.60","name":"延期双金","openPri":"298.60","time":"2019-06-12 15:29:19","totalTurnover":"1561847375.00","totalVol":"5188.00","variety":"Au(T+N2)","yesDayPic":"300.30"},{"closePri":"299.20","highPic":"300.06","limit":"0.82%","lowPic":"296.53","name":"沪金100G","openPri":"296.58","time":"2019-06-12 15:29:56","totalTurnover":"165871468.80","totalVol":"556.00","variety":"Au100g","yesDayPic":"296.76"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"沪 条50G","openPri":"--","time":"2019-06-12 15:30:03","totalTurnover":"--","totalVol":"--","variety":"Au50g","yesDayPic":"255.00"},{"closePri":"299.80","highPic":"300.35","limit":"0.92%","lowPic":"297.70","name":"沪  金95","openPri":"297.70","time":"2019-06-12 15:28:41","totalTurnover":"390235062.50","totalVol":"1304.00","variety":"Au99.95","yesDayPic":"297.06"},{"closePri":"299.64","highPic":"300.51","limit":"0.89%","lowPic":"296.08","name":"沪  金99","openPri":"298.00","time":"2019-06-12 15:30:03","totalTurnover":"36904996000.00","totalVol":"123312.00","variety":"Au99.99","yesDayPic":"297.00"},{"closePri":"280.00","highPic":"280.00","limit":"-3.11%","lowPic":"280.00","name":"I黄金100G","openPri":"280.00","time":"2019-06-12 14:12:10","totalTurnover":"560000.00","totalVol":"2.00","variety":"IAU100G","yesDayPic":"289.00"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"I黄金995","openPri":"--","time":"2019-06-11 20:00:00","totalTurnover":"--","totalVol":"--","variety":"IAU99.5","yesDayPic":"237.80"},{"closePri":"297.50","highPic":"297.80","limit":"0.70%","lowPic":"296.10","name":"I黄金9999","openPri":"296.10","time":"2019-06-12 15:28:18","totalTurnover":"9464519000.00","totalVol":"31790.00","variety":"IAU99.99","yesDayPic":"295.42"},{"closePri":"299.77","highPic":"300.60","limit":"0.83%","lowPic":"296.23","name":"M黄金延期","openPri":"296.33","time":"2019-06-12 15:30:03","totalTurnover":"34467996000.00","totalVol":"115320.00","variety":"MAUTD","yesDayPic":"298.15"},{"closePri":"188.39","highPic":"188.39","limit":"1.64%","lowPic":"188.39","name":"沪  铂95","openPri":"188.39","time":"2019-06-12 14:59:25","totalTurnover":"6782040.00","totalVol":"36.00","variety":"Pt99.95","yesDayPic":"185.35"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * closePri : 299.64
         * highPic : 300.51
         * limit : 0.89%
         * lowPic : 296.08
         * name : 沪金9999
         * openPri : 298.00
         * time : 2019-06-12 15:30:03
         * totalTurnover : 369049952000.00
         * totalVol : 1233126.00
         * variety : AU99.99
         * yesDayPic : 297.00
         */

        private String closePri;
        private String highPic;
        private String limit;
        private String lowPic;
        private String name;
        private String openPri;
        private String time;
        private String totalTurnover;
        private String totalVol;
        private String variety;
        private String yesDayPic;

        public String getClosePri() {
            return closePri;
        }

        public void setClosePri(String closePri) {
            this.closePri = closePri;
        }

        public String getHighPic() {
            return highPic;
        }

        public void setHighPic(String highPic) {
            this.highPic = highPic;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getLowPic() {
            return lowPic;
        }

        public void setLowPic(String lowPic) {
            this.lowPic = lowPic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenPri() {
            return openPri;
        }

        public void setOpenPri(String openPri) {
            this.openPri = openPri;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTotalTurnover() {
            return totalTurnover;
        }

        public void setTotalTurnover(String totalTurnover) {
            this.totalTurnover = totalTurnover;
        }

        public String getTotalVol() {
            return totalVol;
        }

        public void setTotalVol(String totalVol) {
            this.totalVol = totalVol;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }

        public String getYesDayPic() {
            return yesDayPic;
        }

        public void setYesDayPic(String yesDayPic) {
            this.yesDayPic = yesDayPic;
        }
    }
}
