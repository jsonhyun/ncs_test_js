package ncs_test_js.ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncs_test_js.ui.panel.DepartmentUIPanel;
import ncs_test_js.ui.panel.EmployeeUIPanel;
import ncs_test_js.ui.panel.TitleUIPanel;

@SuppressWarnings("serial")
public class ErpMenuFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDept;
	private JButton btnTitle;
	private JButton btnEmp;
	private JFrame deptFrame;
	private JFrame titleFrame;
	private JFrame empFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpMenuFrame frame = new ErpMenuFrame();
					frame.setVisible(true);
					frame.setLocation(800, 200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ErpMenuFrame() {
		initialize();
	}
	private void initialize() {
		setTitle("ERP 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 90);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 10));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmp) {
			btnEmpActionPerformed(e);
		}
		if (e.getSource() == btnTitle) {
			btnTitleActionPerformed(e);
		}
		if (e.getSource() == btnDept) {
			btnDeptActionPerformed(e);
		}
	}
	protected void btnDeptActionPerformed(ActionEvent e) {
		if(deptFrame==null) {
			deptFrame = new JFrame();
			deptFrame.setBounds(100, 100, 450, 400);
			deptFrame.setTitle("부서 관리");
			DepartmentUIPanel dp = new DepartmentUIPanel();
			deptFrame.getContentPane().add(dp);
			deptFrame.setVisible(true);
		}else {
			if(deptFrame.isVisible()) {
				return;
			}
			deptFrame.setVisible(true);
		}
		
		
	}
	protected void btnTitleActionPerformed(ActionEvent e) {
		if(titleFrame == null) {
			titleFrame = new JFrame();
			titleFrame.setBounds(100, 100, 450, 400);
			titleFrame.setTitle("직책 관리");
			TitleUIPanel tp = new TitleUIPanel();
			titleFrame.getContentPane().add(tp);
			titleFrame.setVisible(true);
		}else {
			if(titleFrame.isVisible()) {
				return;
			}
			titleFrame.setVisible(true);
		}
	}
	protected void btnEmpActionPerformed(ActionEvent e) {
		if(empFrame == null) {
			empFrame = new JFrame();
			empFrame.setBounds(100, 100, 550, 700);
			empFrame.setTitle("사원 관리");
			EmployeeUIPanel eu = new EmployeeUIPanel();
			empFrame.getContentPane().add(eu);
			empFrame.setVisible(true);
		}else {
			if(empFrame.isVisible()) {
				return;
			}
			empFrame.setVisible(true);
		}
	}
}
