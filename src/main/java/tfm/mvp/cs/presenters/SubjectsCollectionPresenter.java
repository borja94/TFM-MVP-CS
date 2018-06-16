package tfm.mvp.cs.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.views.SubjectsCollectionView;

public class SubjectsCollectionPresenter implements ISubjectCollectionViewPresenter {

	private SubjectDao subjectDao;
	private List<Subject> subjectsCollection;
	private static final String[] columnNames = { "ID", "Titulo", "Curso" };
	private SubjectsCollectionView subjectCollectionView;
	private ISubjectFormPresenter iSubjectFormPresenter;

	public SubjectsCollectionPresenter() {
		subjectDao = new SubjectDao();
	}

	public void loadTableData() {
		subjectsCollection = subjectDao.getAll();
	}

	public String getColumnName(int position) {
		return columnNames[position];
	}

	public int getNumRows() {
		return subjectsCollection.size();
	}

	public void updateSubjectsTableData() {
		loadTableData();
		String[] columns = new String[columnNames.length];
		String[][] tableData = new String[subjectsCollection.size()][columnNames.length];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = columnNames[i];
		}
		for (int i = 0; i < subjectsCollection.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				tableData[i][j] = getSubjectAtribute(j, i);
			}
		}
		subjectCollectionView.setSubjectsTableModel(new DefaultTableModel(tableData, columns));
		subjectCollectionView.getSubjectTable().setModel(subjectCollectionView.getSubjectsTableModel());
	}
	
	public void removeSubject() {
		int selectedRow = subjectCollectionView.getSubjectTable().getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeSubject(Integer.parseInt(
						subjectCollectionView.getSubjectTable().getValueAt(selectedRow, 0).toString()));
				updateSubjectsTableData();
			}
		}
	}

	public void editMode() {

		int selectedRow = subjectCollectionView.getSubjectTable().getSelectedRow();
		if (selectedRow != -1)
			iSubjectFormPresenter.editSubjectMode(Integer.parseInt(subjectCollectionView.getSubjectsTableModel().getValueAt(selectedRow, 0).toString()));
	}
	
	public void newSubjectMode() {
		iSubjectFormPresenter.newSubjectMode();
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

	@Override
	public void setSubjectCollectionView(SubjectsCollectionView subjectsCollectionView) {
		this.subjectCollectionView = subjectsCollectionView;

	}

	public void setSubjectFormPresenter(ISubjectFormPresenter subjectFormPresenter) {
		this.iSubjectFormPresenter = subjectFormPresenter;
	}

}
