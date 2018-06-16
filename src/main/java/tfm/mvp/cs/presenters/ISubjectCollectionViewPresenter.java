package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.SubjectsCollectionView;

public interface ISubjectCollectionViewPresenter {

	public void updateSubjectsTableData();

	public void removeSubject();

	public void editMode();

	public void newSubjectMode();

	public void setSubjectCollectionView(SubjectsCollectionView subjectsCollectionView);

}
