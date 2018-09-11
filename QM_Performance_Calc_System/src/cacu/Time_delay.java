package cacu;

import cacu.Calculate;

/**
 * 本类定义所有需要计算的值
 * 如果这些计算结果需要以单个报文发送给服务器
 * 在此处将计算结果按照约定序列化方式序列化
 */
public class Time_delay {

    /*OBU-OBU之间的通信参数*/
    private double obu2obu_time_delay;
    private static double obu2obu_average_time_delay;
    private double obu2obu_time_jitter;
    private double obu2obu_missing_rate;

    /*OBU-RSU之间的通信参数*/
    private double obu2rsu_time_delay;
    private static double obu2rsu_average_time_delay;
    private double obu2rsu_time_jitter;
    private double obu2rsu_missing_rate;

    /*RSU-OBU之间的通信参数*/
    private double rsu2obu_time_delay;
    private static double rsu2obu_average_time_delay;
    private double rsu2obu_time_jitter;
    private double rsu2obu_missing_rate;

    /*OBU-数据中心之间的通信参数*/
    private double obu2controlCenter_time_delay;
    private static double obu2controlCenter_average_time_delay;
    private double obu2controlCenter_time_jitter;
    private double obu2controlCenter_missing_rate;

    /*RSU-数据中心之间的通信参数*/
    private double rsu2controlCenter_time_delay;
    private static double rsu2controlCenter_average_time_delay;
    private double rsu2controlCenter_time_jitter;
    private double rsu2controlCenterc_missing_rate;

    Calculate calculate = new Calculate();
    @Override
    public String toString() {
        String res = "";
        String temp;

        //obu之间
        temp = String.valueOf(obu2obu_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2obu_average_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2obu_time_jitter);
        res = res + temp;
        temp = String.valueOf(obu2obu_missing_rate);
        res = res + temp;

        //obu-rsu之间
        temp = String.valueOf(obu2rsu_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2rsu_average_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2rsu_time_jitter);
        res = res + temp;
        temp = String.valueOf(obu2rsu_missing_rate);
        res = res + temp;

        //rsu-obu
        temp = String.valueOf(rsu2obu_time_delay);
        res = res + temp;
        temp = String.valueOf(rsu2obu_average_time_delay);
        res = res + temp;
        temp = String.valueOf(rsu2obu_time_jitter);
        res = res + temp;
        temp = String.valueOf(rsu2obu_missing_rate);
        res = res + temp;

        //obu-数据中心之间
        temp = String.valueOf(obu2controlCenter_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2controlCenter_average_time_delay);
        res = res + temp;
        temp = String.valueOf(obu2controlCenter_time_jitter);
        res = res + temp;
        temp = String.valueOf(obu2controlCenter_missing_rate);
        res = res + temp;

        //rsu-数据中心
        temp = String.valueOf(rsu2controlCenter_time_delay);
        res = res + temp;
        temp = String.valueOf(rsu2controlCenter_average_time_delay);
        res = res + temp;
        temp = String.valueOf(rsu2controlCenter_time_jitter);
        res = res + temp;
        temp = String.valueOf(rsu2controlCenterc_missing_rate);
        res = res + temp;

        return res;
    }

}
