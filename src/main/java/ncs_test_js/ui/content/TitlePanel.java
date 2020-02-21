package ncs_test_js.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ncs_test_js.dto.Title;
import ncs_test_js.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends AbsItemPanel<Title> {
	private JTextField tfNo;
	private JTextField tfName;

	public TitlePanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setEnabled(false);
		add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
	}
	
	public void setNum(Title item) {
		tfNo.setText(String.format("T%03d", item.getTitleNo()+1));
	}
	
	@Override
	public Title getItem() {
		int titleNo = Integer.parseInt(tfNo.getText().substring(3));
		String titleName = tfName.getText().trim();
		return new Title(titleNo, titleName);
	}
	@Override
	public void setItem(Title item) {
		tfNo.setText(String.format("T%03d", item.getTitleNo()));
		tfName.setText(item.getTitleName());
		
	}
	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		
	}
	@Override
	public void validCheck() {
		if(tfNo.getText().equals("") || tfName.getText().equals("")) {
			throw new InvalidCheckException();
		}
	}
}
