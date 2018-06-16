package tfm.mvp.cs.views;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.cs.models.Subject;
import tfm.mvp.cs.models.Teacher;
import tfm.mvp.cs.presenters.TeacherFormPresenter;
import tfm.mvp.cs.presenters.TeachersCollectionPresenter;

public class TeacherFormView extends JPanel {

	private TeacherFormPresenter teacherFormPresenter;

	private DefaultListModel<String> unassignedSubjectModel;
	private DefaultListModel<String> assignedSubjectModel;

	private static final String NEW_TEACHER_LABEL_TEXT = "Nuevo profesor";
	private static final String EDIT_TEACHE_LABEL_TEXT = "Editar profesor";
	private static final String ID_SUBJECT_SEPARATOR = "#";

	private JLabel teacherFormLabel;
	private JButton addSubjectButton;
	private JButton removeSubjectButton;
	private JList<String> assignSubjectCollection;
	private JList<String> unassignSubjectCollection;
	private JTextField nameInput;
	private JTextField surnameInput;
	private JButton saveFormButton;
	private JLabel nameInputLabel;
	private JLabel surnameInputLabel;
	private JLabel unassignSubjectsInputLabel;
	private JLabel assignSubjectInputLabel;
	private JScrollPane unassignSubjectsScrollPane;
	private JScrollPane assignSubjectPane;
	private boolean editMode;
	private int teacherSelectedId;

	public TeacherFormView() {
		initComponents();
		teacherFormPresenter = new TeacherFormPresenter(this);
		updateSubjectList();
	}

	private void initComponents() {

		nameInput = new JTextField();
		nameInputLabel = new JLabel();
		surnameInput = new JTextField();
		surnameInputLabel = new JLabel();
		unassignSubjectCollection = new JList<>();
		assignSubjectCollection = new JList<>();
		addSubjectButton = new JButton();
		removeSubjectButton = new JButton();
		unassignSubjectsInputLabel = new JLabel();
		assignSubjectInputLabel = new JLabel();
		saveFormButton = new JButton();
		unassignSubjectsScrollPane = new JScrollPane();
		assignSubjectPane = new JScrollPane();
		teacherFormLabel = new JLabel();

		nameInputLabel.setText("Nombre");

		surnameInputLabel.setText("Apellidos");
		teacherFormLabel.setText(NEW_TEACHER_LABEL_TEXT);

		addSubjectButton.setText("-->");
		addSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onAddSubjectButtonActionPerformed();
			}
		});

		removeSubjectButton.setText("<--");
		removeSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onRemoveSubjectButtonActionPerformed();
			}
		});

		unassignSubjectsInputLabel.setText("Asignaturas");

		assignSubjectInputLabel.setText("Asignaturas seleccionadas");

		saveFormButton.setText("Guardar");
		saveFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onSaveFormButtonActionPerformed();
			}
		});

		unassignSubjectsScrollPane.setViewportView(unassignSubjectCollection);

		assignSubjectPane.setViewportView(assignSubjectCollection);

		initComponentsPosition();
	}

	public void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(teacherFormLabel)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(nameInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(surnameInputLabel).addComponent(surnameInput,
														GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
														101, GroupLayout.PREFERRED_SIZE)
												.addComponent(unassignSubjectsInputLabel))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(addSubjectButton).addComponent(removeSubjectButton))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(assignSubjectInputLabel)))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117).addComponent(saveFormButton)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(26, 26, 26).addComponent(teacherFormLabel).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameInputLabel)
						.addComponent(surnameInputLabel))
				.addGap(4, 4, 4)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(surnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(56, 56, 56)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(unassignSubjectsInputLabel).addComponent(assignSubjectInputLabel))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(addSubjectButton)
								.addGap(33, 33, 33).addComponent(removeSubjectButton)))
				.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));
	}

	public void onNewTeacherMode() {
		cleanForm();
	}

	public void onEditTeacherMode(int id) {
		editMode = true;
		teacherFormLabel.setText(EDIT_TEACHE_LABEL_TEXT);
		teacherSelectedId = id;
		teacherFormPresenter.editTeacherMode(id);
	}

	private void updateSubjectList() {
		teacherFormPresenter.updateSubjectList();
	}

	private void onAddSubjectButtonActionPerformed() {
		int[] selectedIndex = unassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = unassignedSubjectModel.getElementAt(index);
			assignedSubjectModel.addElement(item);
			unassignedSubjectModel.remove(index);
		}
	}

	private void onRemoveSubjectButtonActionPerformed() {
		int[] selectedIndex = assignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = assignedSubjectModel.getElementAt(index);
			unassignedSubjectModel.addElement(item);
			assignedSubjectModel.remove(index);
		}
	}

	private void onSaveFormButtonActionPerformed() {

		String name = nameInput.getText();
		String surname = surnameInput.getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (editMode)
				teacherFormPresenter.updateTeacher();
			else
				teacherFormPresenter.insertNewTeacher();
		}
	}

	public TeacherFormPresenter getTeacherFormPresenter() {
		return teacherFormPresenter;
	}

	public void setTeacherFormPresenter(TeacherFormPresenter teacherFormPresenter) {
		this.teacherFormPresenter = teacherFormPresenter;
	}

	public DefaultListModel<String> getUnassignedSubjectModel() {
		return unassignedSubjectModel;
	}

	public void setUnassignedSubjectModel(DefaultListModel<String> unassignedSubjectModel) {
		this.unassignedSubjectModel = unassignedSubjectModel;
	}

	public DefaultListModel<String> getAssignedSubjectModel() {
		return assignedSubjectModel;
	}

	public void setAssignedSubjectModel(DefaultListModel<String> assignedSubjectModel) {
		this.assignedSubjectModel = assignedSubjectModel;
	}

	public JLabel getTeacherFormLabel() {
		return teacherFormLabel;
	}

	public void setTeacherFormLabel(JLabel teacherFormLabel) {
		this.teacherFormLabel = teacherFormLabel;
	}

	public JButton getAddSubjectButton() {
		return addSubjectButton;
	}

	public void setAddSubjectButton(JButton addSubjectButton) {
		this.addSubjectButton = addSubjectButton;
	}

	public JButton getRemoveSubjectButton() {
		return removeSubjectButton;
	}

	public void setRemoveSubjectButton(JButton removeSubjectButton) {
		this.removeSubjectButton = removeSubjectButton;
	}

	public JList<String> getAssignSubjectCollection() {
		return assignSubjectCollection;
	}

	public void setAssignSubjectCollection(JList<String> assignSubjectCollection) {
		this.assignSubjectCollection = assignSubjectCollection;
	}

	public JList<String> getUnassignSubjectCollection() {
		return unassignSubjectCollection;
	}

	public void setUnassignSubjectCollection(JList<String> unassignSubjectCollection) {
		this.unassignSubjectCollection = unassignSubjectCollection;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(JTextField nameInput) {
		this.nameInput = nameInput;
	}

	public JTextField getSurnameInput() {
		return surnameInput;
	}

	public void setSurnameInput(JTextField surnameInput) {
		this.surnameInput = surnameInput;
	}

	public JButton getSaveFormButton() {
		return saveFormButton;
	}

	public void setSaveFormButton(JButton saveFormButton) {
		this.saveFormButton = saveFormButton;
	}

	public JLabel getNameInputLabel() {
		return nameInputLabel;
	}

	public void setNameInputLabel(JLabel nameInputLabel) {
		this.nameInputLabel = nameInputLabel;
	}

	public JLabel getSurnameInputLabel() {
		return surnameInputLabel;
	}

	public void setSurnameInputLabel(JLabel surnameInputLabel) {
		this.surnameInputLabel = surnameInputLabel;
	}

	public JLabel getUnassignSubjectsInputLabel() {
		return unassignSubjectsInputLabel;
	}

	public void setUnassignSubjectsInputLabel(JLabel unassignSubjectsInputLabel) {
		this.unassignSubjectsInputLabel = unassignSubjectsInputLabel;
	}

	public JLabel getAssignSubjectInputLabel() {
		return assignSubjectInputLabel;
	}

	public void setAssignSubjectInputLabel(JLabel assignSubjectInputLabel) {
		this.assignSubjectInputLabel = assignSubjectInputLabel;
	}

	public JScrollPane getUnassignSubjectsScrollPane() {
		return unassignSubjectsScrollPane;
	}

	public void setUnassignSubjectsScrollPane(JScrollPane unassignSubjectsScrollPane) {
		this.unassignSubjectsScrollPane = unassignSubjectsScrollPane;
	}

	public JScrollPane getAssignSubjectPane() {
		return assignSubjectPane;
	}

	public void setAssignSubjectPane(JScrollPane assignSubjectPane) {
		this.assignSubjectPane = assignSubjectPane;
	}

	public void setTeacherCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherFormPresenter.setTeachetCollectionPresenter(teacherCollectionPresenter);
	}

	public void cleanForm() {
		teacherFormLabel.setText(NEW_TEACHER_LABEL_TEXT);
		nameInput.setText("");
		surnameInput.setText("");
		editMode = false;
		teacherSelectedId = 0;
	}

	public void setEditTeacher(Teacher teacher) {
		nameInput.setText(teacher.getName());
		surnameInput.setText(teacher.getSurname());
	}

	public Teacher getEditTeacher() {
		Teacher teacher = new Teacher(teacherSelectedId, nameInput.getText(), surnameInput.getText());
		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacher.getSubjectCollection().add(new Subject(aux));
		}
		return teacher;
	}

}
