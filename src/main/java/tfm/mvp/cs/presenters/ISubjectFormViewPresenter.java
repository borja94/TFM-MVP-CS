package tfm.mvp.cs.presenters;

import tfm.mvp.cs.views.SubjectFormView;

public interface ISubjectFormViewPresenter {

	public void insertNewStudent();

	public void updateStudent();

	public void setSubjectFormView(SubjectFormView subjectFormView);

}
