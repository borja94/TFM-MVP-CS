package tfm.mvp.cs.presenters;

import tfm.mvp.cs.models.StudentDao;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.models.TeacherDao;
import tfm.mvp.cs.views.MainView;

public class MainPresenter {

	private StudentDao studentDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;
	private MainView mainView;
	
	public MainPresenter() {
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();
		teacherDao = new TeacherDao();		
	}
	
	private int getNumStudents() {
		return studentDao.getAll().size();
	}
	private int getNumTeachers() {
		return teacherDao.getAll().size();
	}
	
	private int getNumSubjects() {
		return subjectDao.getAll().size();
	}
	
	public void loadEntitiesCounters() {
		mainView.SetTeacherButtonText(getNumTeachers());
		mainView.SetStudentButtonText(getNumStudents());
		mainView.SetSubjectButtonText(getNumSubjects());
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
}
