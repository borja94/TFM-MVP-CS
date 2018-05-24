package tfm.mvp.cs.Presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.Models.Subject;
import tfm.mvp.cs.Models.SubjectDto;
import tfm.mvp.cs.Models.Teacher;
import tfm.mvp.cs.Models.TeacherDto;
import tfm.mvp.cs.Views.TeacherFormView;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private TeacherFormView teacherFormView;
	/*
	 * private boolean EditMode; private int TeacherSelectedId;
	 */
	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void NotifyInsertNewTeacher() {

		Teacher teacher = teacherFormView.getEditTeacher();

		teacherDto.Insert(teacher);
		cleanForm();
		teacherCollectionPresenter.NotifyUpdateTeacherTableData();
	}

	public void NotifyUpdateTeacher() {

		Teacher teacher = teacherFormView.getEditTeacher();

		teacherDto.Update(teacher);
		cleanForm();
		teacherCollectionPresenter.NotifyUpdateTeacherTableData();
	}

	public void NotifyUpdateSubjectList() {

		UpdateSubjectList(null);
	}

	private void UpdateSubjectList(List<String> teacherSubjectCollection) {
		teacherFormView.setUnassignedSubjectModel(new DefaultListModel<>());

		teacherFormView.setAssignedSubjectModel(new DefaultListModel<>());

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject.toString())) {
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
		UpdateSubjectList(null);

	}

	public void NotifyRemoveSubject() {
		int[] selectedIndex = teacherFormView.getAssignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectModel().getElementAt(index).toString();
			teacherFormView.getUnassignedSubjectModel().addElement(item);
			teacherFormView.getAssignedSubjectModel().remove(index);
		}
	}


	public void NotifyNewTeacherMode() {
		cleanForm();
	}

	public void NotifyEditTeacherMode(int id) {
		
		loadTeacher(id);
		teacherFormView.setEditTeacher(teacher);
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getTeacherNumSubject(); i++) {
			subject.add(getTeacherSubject(i));
		}
		UpdateSubjectList(subject);
	}

	public void loadTeacher(int id) {
		teacher = teacherDto.Get(id);
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
		subjectsCollection = subjectDto.GetAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

}
