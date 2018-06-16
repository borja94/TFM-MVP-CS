package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.SubjectFormView;

public interface ISubjectFormViewPresenter {

	public void insertNewStudent(String title, int course);

	public void updateStudent(String title, int course, int id);

	public void setSubjectFormView(SubjectFormView subjectFormView);

}
