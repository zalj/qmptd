package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.chart.ChartPanel;

import cacu.DataBaseOperator;
public class Display {
	
    
    public static void main(String[] args) {    
        JFrame frame1 = new JFrame("Display Demo");
        frame1.setSize(350, 300);
        frame1.setLocationRelativeTo(null);
        frame1.setResizable(false);
        //创建菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        frame1.setJMenuBar(jMenuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem closeItem = new JMenuItem("Exit");
        closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        fileMenu.add(newItem);
        fileMenu.add(closeItem);
        jMenuBar.add(fileMenu);
        
        JMenu editMenu = new JMenu("Edit");
        JMenuItem server = new JMenuItem("Server");
        JMenuItem client = new JMenuItem("Client");
        
        editMenu.add(server);
        editMenu.add(client);
        jMenuBar.add(editMenu);
        
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel numberOfFind = new JLabel("要查询的条目数：");
        numberOfFind.setBounds(25, 10, 120, 20);
        panel.add(numberOfFind);
        
        JTextField numOfFindInput = new JTextField(10);
        numOfFindInput.setBounds(159, 8, 86, 24);
        panel.add(numOfFindInput);
        
        JButton enterButton = new JButton("查询");
        enterButton.setBounds(50, 190, 100, 30);
        panel.add(enterButton);
        frame1.getContentPane().add(panel);
        
        JCheckBox chckbxTd = new JCheckBox("\u65F6\u5EF6");
		chckbxTd.setBounds(25, 35, 100, 30);
		chckbxTd.setSelected(true);
        panel.add(chckbxTd);
        
        JCheckBox chckbxGj = new JCheckBox("\u6B63\u5411\u6296\u52A8");
        chckbxGj.setBounds(25, 70, 100, 30);
        chckbxGj.setSelected(true);
        panel.add(chckbxGj);
        
        JCheckBox chckbxBj = new JCheckBox("\u53CD\u5411\u6296\u52A8");
        chckbxBj.setBounds(125, 70, 100, 30);
        chckbxBj.setSelected(true);
        panel.add(chckbxBj);
        
        JCheckBox chckbxMc = new JCheckBox("\u4E22\u5305\u6570");
        chckbxMc.setBounds(25, 105, 100, 30);
        chckbxMc.setSelected(true);
        panel.add(chckbxMc);
        
        JCheckBox chckbxMr = new JCheckBox("\u4E22\u5305\u7387");
        chckbxMr.setBounds(125, 105, 100, 30);
        chckbxMr.setSelected(true);
        panel.add(chckbxMr);
        
        JCheckBox chckbxAtd = new JCheckBox("\u5E73\u5747\u65F6\u5EF6");
        chckbxAtd.setBounds(125, 35, 100, 30);
        chckbxAtd.setSelected(true);
        panel.add(chckbxAtd);
        
        JCheckBox chckbxTp = new JCheckBox("\u541E\u5410\u91CF");
        chckbxTp.setBounds(225, 105, 100, 30);
        chckbxTp.setSelected(true);
        panel.add(chckbxTp);
        
        JCheckBox chckbxAs = new JCheckBox("\u5168\u9009");
        chckbxAs.setBounds(25, 150, 100, 30);
        panel.add(chckbxAs);
        
        JCheckBox chckbxAus = new JCheckBox("\u5168\u4E0D\u9009");
        chckbxAus.setBounds(125, 150, 100, 30);
        panel.add(chckbxAus);
        
        JButton displayByChartButton = new JButton("\u56FE\u8868");
        displayByChartButton.setBounds(190, 190, 100, 30);
        panel.add(displayByChartButton);
        
        chckbxAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chckbxAus.setSelected(false);
				chckbxAtd.setSelected(true);
				chckbxBj.setSelected(true);
				chckbxGj.setSelected(true);
				chckbxMc.setSelected(true);
				chckbxMr.setSelected(true);
				chckbxTd.setSelected(true);
				chckbxTp.setSelected(true);
			}
		});
        
        chckbxAus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chckbxAs.setSelected(false);
				chckbxAtd.setSelected(false);
				chckbxBj.setSelected(false);
				chckbxGj.setSelected(false);
				chckbxMc.setSelected(false);
				chckbxMr.setSelected(false);
				chckbxTd.setSelected(false);
				chckbxTp.setSelected(false);
			}
		});
        enterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean[] isFindTarget = new boolean[7];
				isFindTarget[0] = chckbxTd.isSelected();
				isFindTarget[1] = chckbxAtd.isSelected();
				isFindTarget[2] = chckbxGj.isSelected();
				isFindTarget[3] = chckbxBj.isSelected();
				isFindTarget[4] = chckbxMc.isSelected();
				isFindTarget[5] = chckbxMr.isSelected();
				isFindTarget[6] = chckbxTp.isSelected();
				
				if(numOfFindInput.getText().equals("")) {
					JFrame frame2 = new JFrame("查询结果");
			        JScrollPane scrollPane;
					try {
						scrollPane = displayLatestData(10, isFindTarget);
						frame2.getContentPane().add(scrollPane, BorderLayout.CENTER);
				        frame2.pack();
				        frame2.setLocationRelativeTo(null);
				        frame2.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					try {
						Integer number = Integer.parseInt(numOfFindInput.getText());
						String[][] maxPackageId = (String[][])(getRealFind(1, new boolean[] {true, true, true, true, true, true, true})[0]);
						if(number > Integer.parseInt(maxPackageId[0][6])){
							number = Integer.parseInt(maxPackageId[0][6]);
						}
						JFrame frame2 = new JFrame("查询结果");
				        JScrollPane scrollPane = displayLatestData(number, isFindTarget);
				        frame2.getContentPane().add(scrollPane, BorderLayout.CENTER);
				        frame2.pack();
				        frame2.setLocationRelativeTo(null);
				        frame2.setResizable(false);
				        frame2.setVisible(true);
					}catch(Exception ex){
						JFrame frame2 = new JFrame("错误");
						frame2.setSize(200, 100);
						JLabel label = new JLabel("Error: 输入有误，请重新输入！");
						frame2.getContentPane().add(label);
						frame2.setLocationRelativeTo(null);
						frame2.setResizable(false);
						frame2.setVisible(true);
					}
				}
			}

			
		});
        
        displayByChartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean[] isFindTarget = new boolean[7];
				isFindTarget[0] = chckbxTd.isSelected();
				isFindTarget[1] = chckbxAtd.isSelected();
				isFindTarget[2] = chckbxGj.isSelected();
				isFindTarget[3] = chckbxBj.isSelected();
				isFindTarget[4] = chckbxMc.isSelected();
				isFindTarget[5] = chckbxMr.isSelected();
				isFindTarget[6] = chckbxTp.isSelected();
				
				if(numOfFindInput.getText().equals("")) {
					JFrame chartFrame = new JFrame("ByChart");
					String[][] temp = null;
					
					try {
						temp = (String[][]) getRealFind(10, new boolean[] {true, true, true, true, true, true, true})[0];
						
						ChartPanel[] panels = new LineChartByThroughPut(temp, isFindTarget).getChartPanel();
						
						int count = 0;
						for(int i = 0; i < panels.length; i++) {
							if(panels[i] != null) {
								chartFrame.add(panels[i]);
								count++;
							}
						}
						
						if(count == 0) {
							if(chckbxTp.isSelected()) {
								JFrame frame2 = new JFrame("错误");
								frame2.setSize(300, 150);
								JLabel label = new JLabel("Error: 吞吐量无法单独查看，因为其作为自变量！");
								frame2.getContentPane().add(label);
								frame2.setLocationRelativeTo(null);
								frame2.setResizable(false);
								frame2.setVisible(true);
							}else {
								JFrame frame2 = new JFrame("错误");
								frame2.setSize(300, 150);
								JLabel label = new JLabel("Error: 请至少选择一项需要查看的数据！");
								frame2.getContentPane().add(label);
								frame2.setLocationRelativeTo(null);
								frame2.setResizable(false);
								frame2.setVisible(true);
							}
						}
						else if(count == 1){
							chartFrame.setVisible(true);
							chartFrame.setBounds(50, 50, 800, 600);
						}else if (count == 2) {
							chartFrame.setLayout(new GridLayout(2, 1, 10, 10));
							chartFrame.setBounds(50, 50, 800, 1200);
							chartFrame.setVisible(true);
						}else if (count == 3) {
							chartFrame.setLayout(new GridLayout(1, 3, 10, 10));
							chartFrame.setBounds(0, 50, 2400, 600);
							chartFrame.setVisible(true);
						}else if(count == 4){
							chartFrame.setLayout(new GridLayout(2, 2, 10, 10));
							chartFrame.setBounds(0, 0, 1600, 1200);
							chartFrame.setVisible(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}else {
					try {
						Integer number = Integer.parseInt(numOfFindInput.getText());
						String[][] maxPackageId = (String[][])(getRealFind(1, new boolean[] {true, true, true, true, true, true, true})[0]);
						if(number > Integer.parseInt(maxPackageId[0][6])){
							number = Integer.parseInt(maxPackageId[0][6]);
						}
						JFrame chartFrame = new JFrame("ByChart");
						String[][] temp = (String[][]) getRealFind(number, new boolean[] {true, true, true, true, true, true, true})[0];
						
						ChartPanel[] panels = new LineChartByThroughPut(temp, isFindTarget).getChartPanel();
						
						int count = 0;
						for(int i = 0; i < panels.length; i++) {
							if(panels[i] != null) {
								chartFrame.add(panels[i]);
								count++;
							}
						}
						if(count == 0) {
							if(chckbxTp.isSelected()) {
								JFrame frame2 = new JFrame("错误");
								frame2.setSize(300, 150);
								JLabel label = new JLabel("Error: 吞吐量无法单独查看，因为其作为自变量！");
								frame2.getContentPane().add(label);
								frame2.setLocationRelativeTo(null);
								frame2.setResizable(false);
								frame2.setVisible(true);
							}else {
								JFrame frame2 = new JFrame("错误");
								frame2.setSize(300, 150);
								JLabel label = new JLabel("Error: 请至少选择一项需要查看的数据！");
								frame2.getContentPane().add(label);
								frame2.setLocationRelativeTo(null);
								frame2.setResizable(false);
								frame2.setVisible(true);
							}
						}
						else if(count == 1){
							chartFrame.setVisible(true);
							chartFrame.setBounds(50, 50, 800, 600);
						}else if (count == 2) {
							chartFrame.setLayout(new GridLayout(2, 1, 10, 10));
							chartFrame.setBounds(50, 50, 800, 1200);
							chartFrame.setVisible(true);
						}else if (count == 3) {
							chartFrame.setLayout(new GridLayout(1, 3, 10, 10));
							chartFrame.setBounds(0, 50, 2400, 600);
							chartFrame.setVisible(true);
						}else if(count == 4){
							chartFrame.setLayout(new GridLayout(2, 2, 10, 10));
							chartFrame.setBounds(0, 0, 1600, 1200);
							chartFrame.setVisible(true);
						}
					}catch(Exception ex){
						ex.printStackTrace();
						JFrame frame2 = new JFrame("错误");
						frame2.setSize(300, 150);
						JLabel label = new JLabel("Error: 输入有误，请重新输入！");
						frame2.getContentPane().add(label);
						frame2.setLocationRelativeTo(null);
						frame2.setResizable(false);
						frame2.setVisible(true);
					}
				}
				
			}
		});
        frame1.setVisible(true);
    }

    
    public static Object[] getRealFind(int numberOfDisplay, boolean[] isFindTarget) throws Exception {
    	if(numberOfDisplay <= 0) 
    		throw new Exception("Display number can not be negative.");
    	Object[] ans = new Object[2];
    	String[][] temp = DataBaseOperator.getInstance().findLatestNRecord(numberOfDisplay);
    	String[] abbr = {"Time Delay", "Average Time Delay", "Good Jitter", "Bad Jitter", "Miss Package Count", "Miss Rate", "ThroughPut"};
    	
    	int col = 0;
    	for(int i = 0; i < isFindTarget.length; i++) 
    		if(isFindTarget[i]) 
    			col++;
    		
    	
    	String[][] realTemp = new String[numberOfDisplay][col];
    	String[] realAbbr = new String[col];
    	for(int i = 0; i < numberOfDisplay; i++) {
    		int index = 0;
    		for(int j = 0; j < temp[0].length; j++) {
    			if(isFindTarget[j]) {
    				realTemp[i][index] = temp[i][j];
    				index++;
    			}
    		}
    	}
    	ans[0] = realTemp;
    	int index = 0;
    	for(int i = 0; i < temp[0].length; i++) {
    		if(isFindTarget[i]) {
    			realAbbr[index] = abbr[i];
    			index++;
			}
    	}
    	ans[1] = realAbbr;
    	return ans;
    }
    
    private static JScrollPane displayLatestData(int numberOfDisplay, boolean[] isFindTarget) throws Exception{
    	
    	String[][] realTemp = (String[][]) getRealFind(numberOfDisplay, isFindTarget)[0];
    	String[] realAbbr = (String[]) getRealFind(numberOfDisplay, isFindTarget)[1];
    	
    	
    	JTable table = new JTable(realTemp,realAbbr);
    	table.setPreferredScrollableViewportSize(new Dimension(500, 300));
    	DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
    	r.setHorizontalAlignment(JLabel.CENTER);   
    	table.setDefaultRenderer(Object.class, r);
    	return new JScrollPane(table);
	}
}