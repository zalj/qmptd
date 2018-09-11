package cacu;

import java.sql.*;


public class DataBaseOperator {
	//数据库连接变量，用来存储建立的数据库连接
	Connection conn = null;
	//实例变量，同来存储产生的唯一实例
	static DataBaseOperator instance = null;
	//构造函数，为实现单例模式，设置为private
	private DataBaseOperator() {
		init();
	}
	
	//初始化方法，在其中建立数据库连接
	void init() {
		try {
			//数据库驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//数据库路径
			String url = "jdbc:mysql://localhost:3306/obu_center?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
			String user = "root";
			String password = "123456";
			//建立并获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//获得该单例类实例的方法
	public static DataBaseOperator getInstance() {
		//如果不存在该类的实例，则创建一个，并保存在instance变量中
		if(instance == null)
			instance = new DataBaseOperator();
		return instance;
	}
	public void insertTest(String t) {
		String sql = "insert into obu_center(time_delay,average_time_delay,good_jitter,bad_jitter,miss_count,missing_rate,throughput) values(1001,1001.3170731707318,0.68292682926824,-1.31707317073176,2,'2.4%',82)";
		Statement stat;
		try {
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertDelayData(String[] DelayData) {
		int timeDelay = Integer.parseInt(DelayData[0]);
		double averageTimeDelay = Double.parseDouble(DelayData[1]);
		double goodJitter = Double.parseDouble(DelayData[2]);
		double badJitter = Double.parseDouble(DelayData[3]);
		int missCount = Integer.parseInt(DelayData[4]);
		String missRate = DelayData[5];
		int throughput = Integer.parseInt(DelayData[6]);
		//创建插入数据库的SQL语句
		String sql = "insert into obu_center(time_delay,average_time_delay,good_jitter,bad_jitter,miss_count,missing_rate,throughput) values(" 
				+ timeDelay + "," + averageTimeDelay + "," + goodJitter + "," + badJitter + ","
				+ missCount +  ",'" + missRate +  "'," + throughput + ")";
//		System.out.println(sql);
		//执行数据库操作
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.execute(sql);
			if(stat != null)
				stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete() {
		String sql = "truncate table obu_center";
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.execute(sql);
			if(stat != null)
				stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] find(int id) {
		String[] timeDelay = new String[7];
		String sql = "select * from obu_center where throughput=" + id;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery(sql);
			if(rs.next() == true) {
				timeDelay[0] = rs.getInt("time_delay") + "";
				timeDelay[1] = rs.getDouble("average_time_delay") + "";
				timeDelay[2] = rs.getDouble("good_jitter") + "";
				timeDelay[3] = rs.getDouble("bad_jitter") + "";
				timeDelay[4] = rs.getInt("miss_count") + "";
				timeDelay[5] = rs.getString("missing_rate");
				timeDelay[6] = rs.getInt("throughput") + "";
			}
			if(stat != null)
				stat.close();
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeDelay;
	}
	
	public String[][] findLatestNRecord(int n) {
		String[][] timeDelay = new String[n][7];
		String sql = "select * from obu_center order by throughput desc limit 0," + n;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery(sql);
			int i = 0;
			while(rs.next() == true) {
				timeDelay[i][0] = rs.getInt("time_delay") + "";
				timeDelay[i][1] = rs.getDouble("average_time_delay") + "";
				timeDelay[i][2] = rs.getDouble("good_jitter") + "";
				timeDelay[i][3] = rs.getDouble("bad_jitter") + "";
				timeDelay[i][4] = rs.getInt("miss_count") + "";
				timeDelay[i][5] = rs.getString("missing_rate");
				timeDelay[i][6] = rs.getInt("throughput") + "";
				i++;
			}
			if(stat != null)
				stat.close();
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeDelay;
	}
	public static void main(String[] args) {
		DataBaseOperator.getInstance().delete();
	}
}




