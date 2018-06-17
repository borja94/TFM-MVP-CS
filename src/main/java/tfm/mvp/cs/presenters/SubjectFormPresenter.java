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

	public void insertNewStudent() {
			Subject subjectAux = subjectFormView.getEditSubject();
			subjectDao.insert(subjectAux);
			subjectCollectionPresenter.updateSubjectsTableData();
	}

	public void updateStudent() {
			Subject subjectAux = subjectFormView.getEditSubject();
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
