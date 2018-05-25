package tfm.mvp.cs.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class MenuFrame extends JFrame {

	private SubjectsFrame subjectsFrame;
	private TeachersFrame teacherFrame;
	private StudentsFrame studentsFrame;

	
	public MenuFrame() {
		MenuPanel menuPanel = new MenuPanel();
		this.setContentPane(menuPanel);
		initComponents();
		subjectsFrame = new SubjectsFrame(this);
		teacherFrame = new TeachersFrame(this);
		studentsFrame = new StudentsFrame(this);

		menuPanel.getStudentFrameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				studentFrameButtonAction();
			}
		});

		menuPanel.getTeacherFrameButton().addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				teacherFrameButtonAction();
			}
		});
		menuPanel.getSubjectFrameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				subjectFrameButtonAction();
			}
		});

	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
	}

	private void teacherFrameButtonAction() {

		this.setVisible(false);
		teacherFrame.setVisible(true);
		dispose();
	}

	private void studentFrameButtonAction() {

		this.setVisible(false);
		studentsFrame.setVisible(true);
		dispose();
	}

	private void subjectFrameButtonAction() {

		this.setVisible(false);
		subjectsFrame.setVisible(true);
		dispose();
	}

}
