package tfm.mvp.cs.presenters;


import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.SubjectDao;
import tfm.mvp.cs.views.SubjectFormView;

public class SubjectFormPresenter implements ISubjectFormPresenter,ISubjectFormViewPresenter{

	private SubjectDao subjectDao;
	private SubjectFormView subjectFormView;
	private SubjectsCollectionPresenter subjectCollectionPresenter;

	public SubjectFormPresenter() {
		subjectDao = new SubjectDao();
	}

	public void insertNewStudent(String title, int course) {
			Subject subjectAux = new Subject(0, title, course);
			subjectDao.insert(subjectAux);
			subjectCollectionPresenter.updateSubjectsTableData();
	}

	public void updateStudent(String title, int course, int id) {
			Subject subjectAux = new Subject(id, title, course);
			subjectDao.update(subjectAux);
			subjectCollectionPresenter.updateSubjectsTableData();
	}

	@Override
	public void setSubjectFormView(SubjectFormView subjectFormView) {
		this.subjectFormView = subjectFormView;
		
	}
	
	public void newSubjectMode() {
		subjectFormView.cleanForm();
	}
	
	public void editSubjectMode(int id) {
		subjectFormView.setEditSubject(subjectDao.get(id));
	}
	
	public void setSubjectCollectionPresenter( SubjectsCollectionPresenter subjectCollectionPresenter) {
		
		this.subjectCollectionPresenter = subjectCollectionPresenter;
	}
}
