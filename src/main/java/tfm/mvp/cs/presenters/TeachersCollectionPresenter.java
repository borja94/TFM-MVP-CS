package tfm.mvp.cs.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.Teacher;
import tfm.mvp.cs.models.TeacherDao;
import tfm.mvp.cs.views.TeacherCollectionView;

public class TeachersCollectionPresenter implements ITeacherCollectionViewPresenter {

	private TeacherDao teacherDao;
	private List<Teacher> teacherCollection;
	private static final String[] columnNames = { "ID", "Nombre", "Apellidos", "Asignaturas" };
	private TeacherCollectionView teacherCollectionView;
	private ITeacherFormPresenter iTeacherFormPresenter;

	public TeachersCollectionPresenter() {
		teacherDao = new TeacherDao();
	}

	public void loadTableData() {
		teacherCollection = teacherDao.getAll();
	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
	}

	public int getNumRows() {
		return teacherCollection.size();
	}

	public String getTeacherAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) teacherCollection.get(row).getId()).toString();
		case 1:
			return teacherCollection.get(row).getName();
		case 2:
			return teacherCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : teacherCollection.get(row).getSubjectCollection()) {
				if (result.toString() == "")
					result.append(subject.getTitle());
				else
					result.append("," + subject.getTitle());
			}
			return result.toString();
		default:
			return null;
		}
	}

	public void removeTeacher(int id) {
		teacherDao.remove(id);
	}

	public void updateTeacherTableData() {

		loadTableData();
		String[] columns = new String[getNumColumns()];
		String[][] tableData = new String[getNumRows()][getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = getColumnName(i);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				tableData[i][j] = getTeacherAtribute(j, i);
			}
		}
		teacherCollectionView.setTeachersTableModel(new DefaultTableModel(tableData, columns));
		teacherCollectionView.getTeachersTable().setModel(teacherCollectionView.getTeachersTableModel());
	}

	public void deleteTeacher() {
		int selectedRow = teacherCollectionView.getTeachersTable().getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al profesor");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeTeacher((Integer.parseInt(
						teacherCollectionView.getTeachersTableModel().getValueAt(selectedRow, 0).toString())));
				updateTeacherTableData();
			}
		}
	}

	public void editTeacher() {
		int selectedRow = teacherCollectionView.getTeachersTable().getSelectedRow();
		if (selectedRow != -1)
			iTeacherFormPresenter.editTeacherMode(Integer
					.parseInt(teacherCollectionView.getTeachersTableModel().getValueAt(selectedRow, 0).toString()));
	}

	public void newTeacher() {
		iTeacherFormPresenter.newTeacherMode();
	}

	@Override
	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView) {
		this.teacherCollectionView = teacherCollectionView;

	}

	public void setTeacherFormPresenter(ITeacherFormPresenter teacherFormPresenter) {
		this.iTeacherFormPresenter = teacherFormPresenter;

	}

}
