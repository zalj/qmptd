package cacu;

/**
 * 
 */
public class Calculate {
    private static int countOfCalculate = 0;
    private static int missCount = 0;

    public static int getCountOfCalculate(){ return countOfCalculate; }
    public static int getMissCount(){ return missCount; }

    public static void addCountOfCalculate(){ countOfCalculate++; }
//    public static void addMissCount() { missCount++; }
//    public static void minusMissCount(){ missCount--; }

    private static long maxTimeDelay = 0;
    private static long minTimeDelay = 0;
    private static long lastPackageId;
    private static double averageTimeDelay; //骞冲潎浼犺緭鏃跺欢

    public static double getAverageTimeDelay() {
		return averageTimeDelay;
	}

	private long timeDelay;               //浼犺緭鏃跺欢
    public double[] jitter = new double[2]; //jitter[0]姝ｅ悜鎶栧姩锛宩itter[1]閫嗗悜鎶栧姩
    private double missingRate;             //涓㈠寘鐜�
    /**
	 * @return the timeDelay
	 */
	public long getTimeDelay() {
		return timeDelay;
	}
	/**
	 * @return the jitter
	 */
	public double[] getJitter() {
		return jitter;
	}
	/**
	 * @return the missing_rate
	 */
	public double getMissingRate() {
		return missingRate;
	}


    Calculate(){addCountOfCalculate();}
    Calculate(long timeStamp, int packageId){
        if(countOfCalculate == 0){
            timeDelay = System.currentTimeMillis() - timeStamp;
            averageTimeDelay = timeDelay;;
            
            maxTimeDelay = timeDelay;
            minTimeDelay = timeDelay;
            
            jitter[0] = timeDelay;
            jitter[1] = timeDelay;
            lastPackageId = packageId;           
            missingRate = 0;
        }else{
        	missCount += packageId - lastPackageId - 1;
        	lastPackageId = packageId;
            timeDelay = System.currentTimeMillis() - timeStamp;
            averageTimeDelay = (averageTimeDelay * countOfCalculate + timeDelay) * 1.0 / (countOfCalculate + 1);
            maxTimeDelay = timeDelay > maxTimeDelay ? timeDelay : maxTimeDelay;
            minTimeDelay = timeDelay < minTimeDelay ? timeDelay : minTimeDelay;
            jitter[0] = averageTimeDelay - minTimeDelay;
            jitter[1] = averageTimeDelay - maxTimeDelay;
            if(missCount + countOfCalculate == 0)
            	missingRate = 0.0;
            else 
            	missingRate = (double)missCount / (double)(countOfCalculate + missCount);
        }
        addCountOfCalculate();
    }
}
