package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.TeacherFormView;

public interface ITeacherFormViewPresenter {

	public void updateSubjectList();

	public void insertNewTeacher();
	
	public void updateTeacher();
	
	public void setTeacherFormView(TeacherFormView teacherFormView);

}
