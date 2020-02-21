package ncs_test_js.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ncs_test_js.dto.Department;
import ncs_test_js.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class DepartmentPanel extends AbsItemPanel<Department> {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	public DepartmentPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblDeptNo = new JLabel("번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		tfDeptNo.setEnabled(false);
		add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);
		
		tfDeptName = new JTextField();
		add(tfDeptName);
		tfDeptName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		add(tfFloor);
		tfFloor.setColumns(10);
	}
	
	public void setNum(Department item) {
		tfDeptNo.setText(String.format("D%03d", item.getDeptNo()+1));
	}
	
	@Override
	public Department getItem() {
		int deptNo = Integer.parseInt(tfDeptNo.getText().substring(3));
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public void setItem(Department item) {
		tfDeptNo.setText(String.format("D%03d", item.getDeptNo()));
		tfDeptName.setText(item.getDeptName());
		tfFloor.setText(item.getFloor()+"");
		
	}

	@Override
	public void clearTf() {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
	}

	@Override
	public void validCheck() {
		if(tfDeptNo.getText().equals("") || tfDeptName.getText().equals("")) {
			throw new InvalidCheckException();
		}
		
	}

}
