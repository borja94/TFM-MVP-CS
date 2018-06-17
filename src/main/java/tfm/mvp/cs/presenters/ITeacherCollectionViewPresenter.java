package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.TeacherCollectionView;

public interface ITeacherCollectionViewPresenter {

	public void updateTeacherTableData();
	
	public void deleteTeacher();
	
	public void editTeacher();
	
	public void newTeacher();
	
	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView);

}
