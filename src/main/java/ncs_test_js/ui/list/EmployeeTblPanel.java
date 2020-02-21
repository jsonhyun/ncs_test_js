package ncs_test_js.ui.list;

import javax.swing.SwingConstants;

import ncs_test_js.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTblPanel extends AbstractTblPanel<Employee> {

	public EmployeeTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		//  empno, empname, title, salary, gender, dno, hire-date
		tableSetWidth(100, 100, 80, 150, 50, 100, 150);
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6);	
		tableCellAlign(SwingConstants.RIGHT, 3);	
	}

	@Override
	protected String[] getColNames() {
		return new String[] {"사원번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}

	@Override
	protected Object[] toArray(Employee item) {	
		String gender = null;
		if(item.getGender() == 0) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		return new Object[] {
			String.format("E%03d", item.getEmpNo()),
			item.getEmpName(),
			String.format("%s",item.getTitle().getTitleName()),
			String.format("%,d", item.getSalary()),//천단위구분기호
			gender,
			String.format("%s(%d층)", item.getDept().getDeptName(), item.getDept().getFloor()),//부서명(부서번호)
			String.format("%tF", item.getHireDate())//입사일
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(String.format("E%03d", item.getEmpNo()), updateIdx, 0);//사원번호
		model.setValueAt(item.getEmpName(), updateIdx, 1);//사원명
		model.setValueAt(item.getTitle(), updateIdx, 2);//직책
		model.setValueAt(String.format("%,d", item.getSalary()), updateIdx, 3);//급여
		String gender = null;
		if(item.getGender() == 0) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		model.setValueAt(gender, updateIdx, 4); //성별
		model.setValueAt(item.getDept(), updateIdx, 5);//소속부서번호
		model.setValueAt(String.format("%tF", item.getHireDate()), updateIdx, 6);
	}

}
