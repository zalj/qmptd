package receive_info;

/**
 * @LastModifyDate锛�2018.6.30 10锛�26
 * @Author: 寮犳尟
 * @function锛氬垱寤烘帴鍙楃嚎绋嬫帴鍙ｏ紝骞跺鎺ュ彈鐨勬秷鎭繘琛岀浉鍏冲鐞嗕互琚鎴风浣跨敤
 */


public class receiveInfo {
    char[] receiveInfo;

    /*OBU-OBU涔嬮棿鐨勯�氫俊鍙傛暟*/
    public double obu2obu_time_delay;
    public static double obu2obu_average_time_delay;
    public double obu2obu_time_jitter;
    public double obu2obu_missing_rate;

    /*OBU-RSU涔嬮棿鐨勯�氫俊鍙傛暟*/
    public double obu2rsu_time_delay;
    public static double obu2rsu_average_time_delay;
    public double obu2rsu_time_jitter;
    public double obu2rsu_missing_rate;

    /*OBU-鏁版嵁涓績涔嬮棿鐨勯�氫俊鍙傛暟*/
    public double obu2controlCenter_time_delay;
    public static double obu2controlCenter_average_time_delay;
    public double obu2controlCenter_time_jitter;
    public double obu2controlCenter_missing_rate;

    /*RSU-鏁版嵁涓績涔嬮棿鐨勯�氫俊鍙傛暟*/
    public double rsu2controlCenter_time_delay;
    public static double rsu2controlCenter_average_time_delay;
    public double rsu2controlCenter_time_jitter;
    public double rsu2controlCenterc_missing_rate;

    /*RSU-OBU涔嬮棿鐨勯�氫俊鍙傛暟*/
    public double rsu2obu_time_delay;
    public static double rsu2obu_average_time_delay;
    public double rsu2obu_time_jitter;
    public double rsu2obu_missing_rate;

}
