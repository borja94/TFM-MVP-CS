package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.StudentsCollectionView;

public interface IStudentCollectionViewPresenter {

	public void updateStudentTableData();

	public void removeStudent();

	public void editStudentMode();

	public void newStudentMode();

	public void setStudentCollectionView(StudentsCollectionView studentsCollectionView);

}
