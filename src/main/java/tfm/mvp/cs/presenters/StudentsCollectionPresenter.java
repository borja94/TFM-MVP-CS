package tfm.mvp.cs.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.cs.models.Student;
import tfm.mvp.cs.models.StudentDao;
import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.views.StudentsCollectionView;

public class StudentsCollectionPresenter implements IStudentCollectionViewPresenter {

	private StudentDao studentDao;
	private List<Student> studentCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Nombre", "Apellidos", "Asignaturas" };
	private IStudentFormPresenter iStudentFormPresenter;
	private StudentsCollectionView studentCollectionView;

	public StudentsCollectionPresenter() {
		studentDao = new StudentDao();
	}

	public void loadTableData() {
		studentCollection = studentDao.getAll();
	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return studentCollection.size();
	}
	
	public void updateStudentTableData() {
		loadTableData();
		String[] columns = new String[getNumColumns()];
		String[][] tableData = new String[getNumRows()][getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i]=getColumnName(i);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				tableData[i][j] = getStudentAtribute(j, i);
			}
		}
		
		studentCollectionView.setStudentsTableModel(new DefaultTableModel(tableData, columns));
		studentCollectionView.getStudentsTable().setModel(studentCollectionView.getStudentsTableModel());
	}

	public void removeStudent() {
		int selectedRow = studentCollectionView.getStudentsTable().getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeStudent((Integer.parseInt(studentCollectionView.getStudentsTableModel().getValueAt(selectedRow, 0).toString())));
				updateStudentTableData();
			}
		}
	}

	public void editStudentMode() {

		int selectedRow = studentCollectionView.getStudentsTable().getSelectedRow();
		if (selectedRow != -1) {
			iStudentFormPresenter.editStudentMode(
					Integer.parseInt(studentCollectionView.getStudentsTableModel().getValueAt(selectedRow, 0).toString()));

		}
	}

	public void newStudentMode() {
		iStudentFormPresenter.newTeacherMode();
	}

	public String getStudentAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) studentCollection.get(row).getId()).toString();
		case 1:
			return studentCollection.get(row).getName();
		case 2:
			return studentCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : studentCollection.get(row).getSubjectCollection()) {
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

	public void removeStudent(int id) {
			studentDao.remove(id);
	}

	public void setStudentFormPresenter(IStudentFormPresenter studentFormPresenter) {
		this.iStudentFormPresenter = studentFormPresenter;
	}

	@Override
	public void setStudentCollectionView(StudentsCollectionView studentsCollectionView) {
		this.studentCollectionView = studentsCollectionView;
		
	}

}
