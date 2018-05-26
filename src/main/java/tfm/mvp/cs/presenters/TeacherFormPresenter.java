package tfm.mvp.cs.presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDto;
import tfm.mvp.cs.models.Teacher;
import tfm.mvp.cs.models.TeacherDto;
import tfm.mvp.cs.views.TeacherFormView;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private TeacherFormView teacherFormView;
	
	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void notifyInsertNewTeacher() {

		Teacher teacherAux = teacherFormView.getEditTeacher();

		teacherDto.insert(teacherAux);
		cleanForm();
		teacherCollectionPresenter.notifyUpdateTeacherTableData();
	}

	public void notifyUpdateTeacher() {

		Teacher teacherAux = teacherFormView.getEditTeacher();

		teacherDto.update(teacherAux);
		cleanForm();
		teacherCollectionPresenter.notifyUpdateTeacherTableData();
	}

	public void notifyUpdateSubjectList() {

		updateSubjectList(null);
	}

	private void updateSubjectList(List<String> teacherSubjectCollection) {
		teacherFormView.setUnassignedSubjectModel(new DefaultListModel<>());

		teacherFormView.setAssignedSubjectModel(new DefaultListModel<>());

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject)) {
				teacherFormView.getAssignedSubjectModel().addElement(subject);
			} else {
				teacherFormView.getUnassignedSubjectModel().addElement(subject);
			}
		}
		teacherFormView.getUnassignSubjectCollection().setModel(teacherFormView.getUnassignedSubjectModel());
		teacherFormView.getAssignSubjectCollection().setModel(teacherFormView.getAssignedSubjectModel());
	}

	private void cleanForm() {

		teacherFormView.cleanForm();
		updateSubjectList(null);

	}

	public void notifyRemoveSubject() {
		int[] selectedIndex = teacherFormView.getAssignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectModel().getElementAt(index);
			teacherFormView.getUnassignedSubjectModel().addElement(item);
			teacherFormView.getAssignedSubjectModel().remove(index);
		}
	}


	public void notifyNewTeacherMode() {
		cleanForm();
	}

	public void notifyEditTeacherMode(int id) {
		
		loadTeacher(id);
		teacherFormView.setEditTeacher(teacher);
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getTeacherNumSubject(); i++) {
			subject.add(getTeacherSubject(i));
		}
		updateSubjectList(subject);
	}

	public void loadTeacher(int id) {
		teacher = teacherDto.get(id);
	}

	public String getTeacherName() {
		return teacher.getName();
	}

	public String getTeacherSurName() {
		return teacher.getSurname();
	}

	public int getTeacherId() {
		return teacher.getId();
	}

	public int getTeacherNumSubject() {
		return teacher.getSubjectCollection().size();
	}

	public String getTeacherSubject(int id) {
		return teacher.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDto.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

}
