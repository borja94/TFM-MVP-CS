package tfm.mvp.cs.Presenters;


import tfm.mvp.cs.Models.Subject;
import tfm.mvp.cs.Models.SubjectDto;

public class SubjectFormPresenter {

	private SubjectDto subjectDto;
	private Subject subject;

	public SubjectFormPresenter() {
		subjectDto = new SubjectDto();
	}

	public void InsertNewStudent(String title, int course) {
		
			Subject subject = new Subject(0, title, course);
			subjectDto.insert(subject);
		
	}

	public void UpdateStudent(String title, int course, int id) {
		
			Subject subject = new Subject(id, title, course);
			subjectDto.update(subject);

		
	}

	public void loadSubject(int id) {
		
			subject = subjectDto.get(id);
		
	}
	public String getSubjectTitle() {
		return subject.getTitle();
	}
	public String getSubjectCourse() {
		return subject.getCourse().toString();
	}
}
