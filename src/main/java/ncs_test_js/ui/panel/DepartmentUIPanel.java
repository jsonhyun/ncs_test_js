package ncs_test_js.ui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_js.dto.Department;
import ncs_test_js.ui.content.DepartmentPanel;
import ncs_test_js.ui.exception.InvalidCheckException;
import ncs_test_js.ui.list.DepartmentTblPanel;
import ncs_test_js.ui.service.DepartmentUIService;

@SuppressWarnings("serial")
public class DepartmentUIPanel extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JButton btnCancel;
	private DepartmentUIService service;
	private DepartmentPanel pDepartment;
	private DepartmentTblPanel pDeptList;

	public DepartmentUIPanel() {
		service = new DepartmentUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pDepartment = new DepartmentPanel();
		pDepartment.setNum(service.lastDepartment());
		pContent.add(pDepartment, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pDeptList = new DepartmentTblPanel();
		pDeptList.loadDate(service.showDepartmentList());
		pDeptList.setPopupMenu(createPopupMenu());
		pList.add(pDeptList, BorderLayout.CENTER);
		
	}
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopupMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener myPopupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("수정")) {
				Department upDept = pDeptList.getSelectedItem();
				pDepartment.setItem(upDept);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().equals("삭제")) {
				Department delDept = pDeptList.getSelectedItem();
				service.removeDepartment(delDept);
				pDeptList.removeRow();
			}
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("추가")) {
				btnAddActionPerformed(e);
			}else {
				btnUpdateActionPerformed(e);
			}
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		
	}
	private void btnUpdateActionPerformed(ActionEvent e) {
		Department upDept = pDepartment.getItem();
		service.modifyDepartment(upDept);
		pDeptList.updateRow(upDept, pDeptList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pDepartment.clearTf();
		pDepartment.setNum(service.lastDepartment());
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Department newDept = pDepartment.getItem();
			service.addDepartment(newDept);
			pDeptList.addItem(newDept);
			pDepartment.clearTf();
			pDepartment.setNum(newDept);
			
		}catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch(Exception e1) {
			SQLException e2 = (SQLException) e1;
			if (e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "부서번호가 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pDepartment.clearTf();
		pDepartment.setNum(service.lastDepartment());
		btnAdd.setText("추가");
	}
}
