package tfm.mvp.cs.presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.models.Teacher;
import tfm.mvp.cs.models.TeacherDao;
import tfm.mvp.cs.views.TeacherFormView;

public class TeacherFormPresenter {

	private TeacherDao teacherDao;
	private SubjectDao subjectDao;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private TeacherFormView teacherFormView;
	
	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
		teacherDao = new TeacherDao();
		subjectDao = new SubjectDao();
	}

	public void insertNewTeacher() {

		Teacher teacherAux = teacherFormView.getEditTeacher();

		teacherDao.insert(teacherAux);
		cleanForm();
		teacherCollectionPresenter.updateTeacherTableData();
	}

	public void updateTeacher() {

		Teacher teacherAux = teacherFormView.getEditTeacher();

		teacherDao.update(teacherAux);
		cleanForm();
		teacherCollectionPresenter.updateTeacherTableData();
	}

	public void updateSubjectList() {

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

	public void removeSubject() {
		int[] selectedIndex = teacherFormView.getAssignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectModel().getElementAt(index);
			teacherFormView.getUnassignedSubjectModel().addElement(item);
			teacherFormView.getAssignedSubjectModel().remove(index);
		}
	}


	public void newTeacherMode() {
		cleanForm();
	}

	public void editTeacherMode(int id) {
		
		loadTeacher(id);
		teacherFormView.setEditTeacher(teacher);
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getTeacherNumSubject(); i++) {
			subject.add(getTeacherSubject(i));
		}
		updateSubjectList(subject);
	}

	public void loadTeacher(int id) {
		teacher = teacherDao.get(id);
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
		subjectsCollection = subjectDao.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

}
