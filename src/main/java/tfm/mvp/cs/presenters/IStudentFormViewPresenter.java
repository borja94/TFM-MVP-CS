package tfm.mvp.cs.presenters;


import tfm.mvp.cs.views.StudentFormView;

public interface IStudentFormViewPresenter {

	public void updateSubjectList();

	public void updateStudent();

	public void insertNewStudent();

	public void setStudentFormView(StudentFormView studentFormView);

}
