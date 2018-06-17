package tfm.mvp.cs.presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.models.Student;
import tfm.mvp.cs.models.StudentDao;
import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.views.StudentFormView;

public class StudentFormPresenter implements IStudentFormPresenter, IStudentFormViewPresenter {

	private StudentDao studentDao;
	private SubjectDao subjectDao;
	private Student student;
	private List<Subject> subjectsCollection;
	private StudentsCollectionPresenter studentCollectionPresenter;
	private StudentFormView studentFormView;

	public StudentFormPresenter() {
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();
	}
	public void updateSubjectList() {
		updateSubjectList(null);
	}
	public void updateSubjectList(List<String> studentSubjectCollection) {
		studentFormView.setUnassignedSubjectModel(new DefaultListModel<>());

		studentFormView.setAssignedSubjectModel(new DefaultListModel<>());

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (studentSubjectCollection != null && studentSubjectCollection.contains(subject)) {
				studentFormView.getAssignedSubjectModel().addElement(subject);
			} else {
				studentFormView.getUnassignedSubjectModel().addElement(subject);
			}
		}
		studentFormView.getUnassignSubjectCollection().setModel(studentFormView.getUnassignedSubjectModel());
		studentFormView.getAssignSubjectCollection().setModel(studentFormView.getAssignedSubjectModel());
	}
	
	public void newTeacherMode() {
		studentFormView.cleanForm();
		updateSubjectList(null);

	}

	public void editStudentMode(int id) {
		
		loadStudent(id);
		studentFormView.setEditStudent(student);
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getStudentNumSubject(); i++) {
			subject.add(getStudentSubject(i));
		}
		updateSubjectList(subject);
	}

	public void insertNewStudent() {

		Student studentAux = studentFormView.getEditStudent();
		studentDao.insert(studentAux);
		studentCollectionPresenter.updateStudentTableData();
	}

	public void updateStudent() {

		Student studentAux = studentFormView.getEditStudent();
		studentDao.update(studentAux);
		studentCollectionPresenter.updateStudentTableData();
	}

	public void loadStudent(int id) {
		student = studentDao.get(id);
	}

	public String getStudentName() {
		return student.getName();
	}

	public String getStudentSurName() {
		return student.getSurname();
	}

	public int getStudentId() {
		return student.getId();
	}

	public int getStudentNumSubject() {
		return student.getSubjectCollection().size();
	}

	public String getStudentSubject(int id) {
		return student.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDao.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setStudentCollectionPresenter(StudentsCollectionPresenter studentCollectionPresenter) {

		this.studentCollectionPresenter = studentCollectionPresenter;
	}

	@Override
	public void setStudentFormView(StudentFormView studentFormView) {
		this.studentFormView = studentFormView;
	}
}
