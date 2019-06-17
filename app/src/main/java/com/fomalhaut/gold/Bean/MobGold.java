package com.fomalhaut.gold.Bean;

import java.util.List;

public class MobGold {

    /**
     * msg : success
     * result : [{"closePri":"302.23","highPic":"306.01","limit":"-0.92%","lowPic":"301.20","name":"沪金9999","openPri":"306.00","time":"2019-06-17 10:29:57","totalTurnover":"144885968000.00","totalVol":"479250.00","variety":"AU99.99","yesDayPic":"305.04"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"黄金995","openPri":"--","time":"2019-06-16 20:00:00","totalTurnover":"--","totalVol":"--","variety":"AU995","yesDayPic":"271.60"},{"closePri":"302.16","highPic":"305.45","limit":"-0.98%","lowPic":"301.12","name":"黄金延期","openPri":"304.90","time":"2019-06-17 10:30:09","totalTurnover":"25934676000.00","totalVol":"85362.00","variety":"Au(T+D)","yesDayPic":"302.60"},{"closePri":"307.35","highPic":"313.00","limit":"0.64%","lowPic":"305.30","name":"延期单金","openPri":"306.60","time":"2019-06-17 10:27:07","totalTurnover":"145136000.00","totalVol":"470.00","variety":"Au(T+N1)","yesDayPic":"302.65"},{"closePri":"304.70","highPic":"317.00","limit":"-0.85%","lowPic":"303.65","name":"延期双金","openPri":"317.00","time":"2019-06-17 10:30:09","totalTurnover":"340383218.80","totalVol":"1112.00","variety":"Au(T+N2)","yesDayPic":"304.95"},{"closePri":"301.59","highPic":"305.25","limit":"-0.90%","lowPic":"301.51","name":"沪金100G","openPri":"305.25","time":"2019-06-17 10:27:31","totalTurnover":"44842519.50","totalVol":"148.00","variety":"Au100g","yesDayPic":"304.33"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"沪 条50G","openPri":"--","time":"2019-06-16 20:00:00","totalTurnover":"--","totalVol":"--","variety":"Au50g","yesDayPic":"255.00"},{"closePri":"302.20","highPic":"305.00","limit":"-0.92%","lowPic":"301.80","name":"沪  金95","openPri":"305.00","time":"2019-06-17 10:27:52","totalTurnover":"11497660.20","totalVol":"38.00","variety":"Au99.95","yesDayPic":"305.02"},{"closePri":"302.23","highPic":"306.01","limit":"-0.92%","lowPic":"301.20","name":"沪  金99","openPri":"306.00","time":"2019-06-17 10:29:57","totalTurnover":"14488597000.00","totalVol":"47925.00","variety":"Au99.99","yesDayPic":"305.04"},{"closePri":"300.00","highPic":"300.00","limit":"7.14%","lowPic":"300.00","name":"I黄金100G","openPri":"300.00","time":"2019-06-17 10:15:01","totalTurnover":"1800000.00","totalVol":"6.00","variety":"IAU100G","yesDayPic":"280.00"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"I黄金995","openPri":"--","time":"2019-06-16 20:00:00","totalTurnover":"--","totalVol":"--","variety":"IAU99.5","yesDayPic":"237.80"},{"closePri":"298.90","highPic":"302.00","limit":"-1.18%","lowPic":"298.40","name":"I黄金9999","openPri":"302.00","time":"2019-06-17 10:30:05","totalTurnover":"4850497500.00","totalVol":"16230.00","variety":"IAU99.99","yesDayPic":"302.47"},{"closePri":"302.23","highPic":"306.11","limit":"-0.94%","lowPic":"301.28","name":"M黄金延期","openPri":"305.00","time":"2019-06-17 10:30:09","totalTurnover":"27781718000.00","totalVol":"91508.00","variety":"MAUTD","yesDayPic":"302.88"},{"closePri":"185.18","highPic":"185.18","limit":"-1.24%","lowPic":"184.90","name":"沪  铂95","openPri":"184.90","time":"2019-06-17 10:09:52","totalTurnover":"3331439.90","totalVol":"18.00","variety":"Pt99.95","yesDayPic":"187.50"}]
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
         * closePri : 302.23
         * highPic : 306.01
         * limit : -0.92%
         * lowPic : 301.20
         * name : 沪金9999
         * openPri : 306.00
         * time : 2019-06-17 10:29:57
         * totalTurnover : 144885968000.00
         * totalVol : 479250.00
         * variety : AU99.99
         * yesDayPic : 305.04
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
