package ncs_test_js.ui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ncs_test_js.dto.Title;
import ncs_test_js.ui.content.TitlePanel;
import ncs_test_js.ui.exception.InvalidCheckException;
import ncs_test_js.ui.list.TitleTblPanel;
import ncs_test_js.ui.service.TitleUIService;

@SuppressWarnings("serial")
public class TitleUIPanel extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JButton btnCancel;
	private TitleUIService service;
	private TitlePanel pTitle;
	private TitleTblPanel pTitleList;

	public TitleUIPanel() {
		service = new TitleUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pTitle = new TitlePanel();
		pTitle.setNum(service.lastTitle());
		pContent.add(pTitle, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		pBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		pTitleList = new TitleTblPanel();
		pTitleList.loadDate(service.showTitleList());
		pTitleList.setPopupMenu(createPopupMenu());
		pList.add(pTitleList, BorderLayout.CENTER);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener myPopMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("수정")) {
				Title upTitle = pTitleList.getSelectedItem();
				pTitle.setItem(upTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().equals("삭제")) {
				Title delTitle = pTitleList.getSelectedItem();
				service.removeTitle(delTitle);
				pTitleList.removeRow();
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
		Title newTitle = pTitle.getItem();
		service.modifyTitle(newTitle);
		pTitleList.updateRow(newTitle, pTitleList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pTitle.clearTf();
		pTitle.setNum(service.lastTitle());
		
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Title newTitle = pTitle.getItem();
			service.addTitle(newTitle);
			pTitleList.addItem(newTitle);
			pTitle.clearTf();
			pTitle.setNum(service.lastTitle());
			
		}catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch (Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "직책번호가 중복");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pTitle.clearTf();
		pTitle.setNum(service.lastTitle());
		btnAdd.setText("추가");
	}
}
