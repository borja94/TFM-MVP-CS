package tfm.mvp.cs.presenters;


import javax.swing.DefaultListModel;

import tfm.mvp.cs.views.StudentFormView;

public interface IStudentFormViewPresenter {

	public void updateSubjectList();

	public void updateStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id);

	public void insertNewStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel);

	public void setStudentFormView(StudentFormView studentFormView);

}
