package tfm.mvp.cs.presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.models.Teacher;
import tfm.mvp.cs.models.TeacherDao;
import tfm.mvp.cs.views.TeacherFormView;

public class TeacherFormPresenter implements ITeacherFormPresenter, ITeacherFormViewPresenter {

	private TeacherDao teacherDao;
	private SubjectDao subjectDao;
	private TeacherFormView teacherFormView;

	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherFormPresenter() {
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

		List<Subject> subjectsCollection = subjectDao.getAll();

		for (int i = 0; i < subjectsCollection.size(); i++) {
			String subject = subjectsCollection.get(i).toString();
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

	public void newTeacherMode() {
		cleanForm();
	}

	public void editTeacherMode(int id) {

		Teacher teacher = teacherDao.get(id);
		teacherFormView.setEditTeacher(teacher);
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < teacher.getSubjectCollection().size(); i++) {
			subject.add(teacher.getSubjectCollection().get(i).toString());
		}
		updateSubjectList(subject);
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

	@Override
	public void setTeacherFormView(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;

	}

}
