package tfm.mvp.cs.presenters;

import java.util.List;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;

public class SubjectsCollectionPresenter {

	private SubjectDao subjectDao;
	private List<Subject> subjectsCollection;
	private static final String[] columnNames = { "ID", "Titulo", "Curso" };

	public SubjectsCollectionPresenter() {
		subjectDao = new SubjectDao();
	}

	public void loadTableData() {
		subjectsCollection = subjectDao.getAll();

	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
	}

	public int getNumRows() {
		return subjectsCollection.size();
	}

	public String getSubjectAtribute(int column, int row) {

		switch (column) {
		case 0:
			return subjectsCollection.get(row).getId().toString();
		case 1:
			return subjectsCollection.get(row).getTitle();
		case 2:
			return subjectsCollection.get(row).getCourse().toString();
		default:
			return null;
		}
	}

	public void removeSubject(int id) {
		subjectDao.remove(id);
	}

}
