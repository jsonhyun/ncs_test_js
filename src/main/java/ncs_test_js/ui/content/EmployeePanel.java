package ncs_test_js.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import ncs_test_js.dto.Department;
import ncs_test_js.dto.Employee;
import ncs_test_js.dto.Title;
import ncs_test_js.ui.exception.InvalidCheckException;
import ncs_test_js.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JPanel pEmployee;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JDateChooser tfHireDate;
	private JRadioButton rdbtnWomen;
	private JRadioButton rdbtnMan;
	private JSpinner spSalary;
	private ButtonGroup bg;
	private EmployeeUIService service;

	public EmployeePanel() {

		initialize();
	}
	private void initialize() {
		setBorder(new EmptyBorder(0, 50, 0, 50));
		setLayout(new BorderLayout(0, 0));
		
		pEmployee = new JPanel();
		add(pEmployee, BorderLayout.CENTER);
		pEmployee.setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblEmpNo = new JLabel("번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		tfEmpNo.setEnabled(false);
		pEmployee.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblEmpName);
		
		tfEmpName = new JTextField();
		pEmployee.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pEmployee.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblSalary);
		
		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		pEmployee.add(spSalary);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblGender);
		
		JPanel pGender = new JPanel();
		pEmployee.add(pGender);
		pGender.setLayout(new GridLayout(0, 2, 20, 0));
		
		rdbtnMan = new JRadioButton("남");
		rdbtnWomen = new JRadioButton("여");	
		
		bg = new ButtonGroup();
		bg.add(rdbtnMan);
		bg.add(rdbtnWomen);
		
		rdbtnMan.setSelected(true);
		rdbtnMan.setHorizontalAlignment(SwingConstants.RIGHT);
		pGender.add(rdbtnMan);
		pGender.add(rdbtnWomen);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblDept);
		
		cmbDept = new JComboBox<>();
		pEmployee.add(cmbDept);
		
		JLabel lblhireDate = new JLabel("입사일");
		lblhireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmployee.add(lblhireDate);
		
		tfHireDate = new JDateChooser(new Date(), "yyyy-MM-dd hh:mm");
		pEmployee.add(tfHireDate);
	}

	public void setNum(Employee item) {
		tfEmpNo.setText(String.format("E%03d", item.getEmpNo()+1));
	}
	
	public void setService(EmployeeUIService service) {
		this.service = service;
		setCmbDeptList(service.showDeptList());
		setCmbTitleList(service.showTitleList());
	}
	
	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(0);
	}
	
	public void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(0);
	}
	
	@Override
	public Employee getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfEmpNo.getText().substring(3));
		String empName = tfEmpName.getText().trim();
		Title title = (Title)cmbTitle.getSelectedItem();
		int salary = (int)spSalary.getValue();
		
		int gender = 0;
		if(rdbtnMan.isSelected()) {
			gender = 0;
		}else {
			gender = 1;
		}
		
		Department dept = (Department)cmbDept.getSelectedItem();
		Date hireDate = tfHireDate.getDate();
		return new Employee(empNo, empName, title, salary, gender, dept, hireDate);
	}

	@Override
	public void setItem(Employee item) {
		tfEmpNo.setText(String.format("E%03d", item.getEmpNo()));
		tfEmpName.setText(item.getEmpName());
		cmbTitle.setSelectedItem(item.getTitle());
		spSalary.setValue(item.getSalary());
		if(item.getGender() == 0) {
			rdbtnMan.setSelected(true);
		}else {
			rdbtnWomen.setSelected(true);
		}
		cmbDept.setSelectedItem(item.getDept());
		tfHireDate.setDate(item.getHireDate());
	}

	@Override
	public void clearTf() {
		tfEmpNo.setText("");
		tfEmpName.setText("");;
		cmbTitle.setSelectedIndex(-1);
		spSalary.setValue(1500000);
		rdbtnMan.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		tfHireDate.setDate(new Date());
	}

	@Override
	public void validCheck() {
		if(tfEmpName.getText().equals("") || cmbDept.getSelectedIndex() == -1 || cmbTitle.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}
	}
	
	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}

	public JComboBox<Title> getCmbTitle() {
		return cmbTitle;
	}

}
