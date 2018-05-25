package tfm.mvp.cs.presenters;

import java.util.List;

import tfm.mvp.cs.models.Student;
import tfm.mvp.cs.models.StudentDto;
import tfm.mvp.cs.models.Subject;

public class StudentsCollectionPresenter {

	private StudentDto studentDto;
	private List<Student> studentCollection;
	private final String columnNames[] = { "ID", "Nombre", "Apellidos", "Asignaturas" };

	public StudentsCollectionPresenter() {
		studentDto = new StudentDto();
	}

	public void loadTableData() {
		studentCollection = studentDto.getAll();
	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
	}

	public int getNumRows() {
		return studentCollection.size();
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
			String result = "";
			for (Subject subject : studentCollection.get(row).getSubjectCollection()) {
				if (result == "")
					result = subject.getTitle();
				else
					result += "," + subject.getTitle();
			}
			return result;
		default:
			return null;
		}
	}

	public void RemoveStudent(int id) {
			studentDto.remove(id);
	}

}