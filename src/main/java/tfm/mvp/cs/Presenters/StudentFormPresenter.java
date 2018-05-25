package tfm.mvp.cs.presenters;

import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.models.Student;
import tfm.mvp.cs.models.StudentDto;
import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDto;

public class StudentFormPresenter {

	private StudentDto studentDto;
	private SubjectDto subjectDto;
	private Student student;
	private List<Subject> subjectsCollection;

	public StudentFormPresenter() {
		studentDto = new StudentDto();
		subjectDto = new SubjectDto();
	}

	public void InsertNewStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Student student = new Student(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			student.getSubjectCollection().add(new Subject(aux));
		}
		studentDto.insert(student);

	}

	public void UpdateStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Student student = new Student(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			student.getSubjectCollection().add(new Subject(aux));
		}

		studentDto.update(student);

	}

	public void loadStudent(int id) {
		student = studentDto.get(id);
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
		subjectsCollection = subjectDto.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}
}
