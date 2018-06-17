package tfm.mvp.cs.views;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.cs.models.Student;
import tfm.mvp.cs.presenters.IStudentFormViewPresenter;

public class StudentFormView extends JPanel {

	private JButton addSubjectButton;
	private JList<String> assignSubjectCollection;
	private JTextField nameInput;
	private JButton removeSubjectButton;
	private JButton saveFormButton;
	private JLabel studentFormLabel;
	private JTextField surnameInput;
	private JList<String> unassignSubjectCollection;
	private JLabel nameInputLabel;
	private JLabel surnameInputLabel;
	private JScrollPane unassignSubjectPane;
	private JScrollPane assignSubjectPane;

	private DefaultListModel<String> unassignedSubjectModel;
	private DefaultListModel<String> assignedSubjectModel;
	private boolean editMode;
	private int studentSelectedId;
	private IStudentFormViewPresenter iStudentFormViewPresenter;
	private static final String NEW_STUDENT_LABEL_TEXT = "Nuevo alumno";
	private static final String EDIT_STUDENT_LABEL_TEXT = "Editar alumno";

	public StudentFormView(IStudentFormViewPresenter studentFormViewPresenter) {
		iStudentFormViewPresenter = studentFormViewPresenter;
		iStudentFormViewPresenter.setStudentFormView(this);

		initComponents();
	}

	private void initComponents() {

		studentFormLabel = new JLabel();
		nameInput = new JTextField();
		surnameInput = new JTextField();
		nameInputLabel = new JLabel();
		surnameInputLabel = new JLabel();
		unassignSubjectPane = new JScrollPane();
		unassignSubjectCollection = new JList<>();
		assignSubjectCollection = new JList<>();
		addSubjectButton = new JButton();
		removeSubjectButton = new JButton();
		saveFormButton = new JButton();
		assignSubjectPane = new JScrollPane();

		studentFormLabel.setText(NEW_STUDENT_LABEL_TEXT);

		nameInputLabel.setText("Nombre");

		surnameInputLabel.setText("Apellidos");

		unassignSubjectPane.setViewportView(unassignSubjectCollection);

		assignSubjectPane.setViewportView(assignSubjectCollection);

		addSubjectButton.setText("-->");
		addSubjectButton.addActionListener(e -> onAddSubjectButtonActionPerformed());

		removeSubjectButton.setText("<--");
		removeSubjectButton.addActionListener(e->onRemoveSubjectButtonActionPerformed());

		saveFormButton.setText("Guardar");
		saveFormButton.addActionListener(e->onSaveFormButtonActionPerformed());

		initComponentsPosition();
		iStudentFormViewPresenter.updateSubjectList();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(studentFormLabel)
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
										.addComponent(unassignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(addSubjectButton).addComponent(removeSubjectButton))
										.addGap(18, 18, 18).addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
												101, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117).addComponent(saveFormButton)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(26, 26, 26).addComponent(studentFormLabel).addGap(18, 18, 18)
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
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(unassignSubjectPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(addSubjectButton)
								.addGap(33, 33, 33).addComponent(removeSubjectButton)))
				.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));

	}

	public void cleanForm() {
		studentFormLabel.setText(NEW_STUDENT_LABEL_TEXT);
		nameInput.setText("");
		surnameInput.setText("");
		studentSelectedId = 0;
		editMode = false;
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
				iStudentFormViewPresenter.updateStudent(name, surname, assignedSubjectModel, studentSelectedId);
			else
				iStudentFormViewPresenter.insertNewStudent(name, surname, assignedSubjectModel);
		}
	}

	public JButton getAddSubjectButton() {
		return addSubjectButton;
	}

	public void setAddSubjectButton(JButton addSubjectButton) {
		this.addSubjectButton = addSubjectButton;
	}

	public JList<String> getAssignSubjectCollection() {
		return assignSubjectCollection;
	}

	public void setAssignSubjectCollection(JList<String> assignSubjectCollection) {
		this.assignSubjectCollection = assignSubjectCollection;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(JTextField nameInput) {
		this.nameInput = nameInput;
	}

	public JButton getRemoveSubjectButton() {
		return removeSubjectButton;
	}

	public void setRemoveSubjectButton(JButton removeSubjectButton) {
		this.removeSubjectButton = removeSubjectButton;
	}

	public JButton getSaveFormButton() {
		return saveFormButton;
	}

	public void setSaveFormButton(JButton saveFormButton) {
		this.saveFormButton = saveFormButton;
	}

	public JLabel getStudentFormLabel() {
		return studentFormLabel;
	}

	public void setStudentFormLabel(JLabel studentFormLabel) {
		this.studentFormLabel = studentFormLabel;
	}

	public JTextField getSurnameInput() {
		return surnameInput;
	}

	public void setSurnameInput(JTextField surnameInput) {
		this.surnameInput = surnameInput;
	}

	public JList<String> getUnassignSubjectCollection() {
		return unassignSubjectCollection;
	}

	public void setUnassignSubjectCollection(JList<String> unassignSubjectCollection) {
		this.unassignSubjectCollection = unassignSubjectCollection;
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

	public JScrollPane getUnassignSubjectPane() {
		return unassignSubjectPane;
	}

	public void setUnassignSubjectPane(JScrollPane unassignSubjectPane) {
		this.unassignSubjectPane = unassignSubjectPane;
	}

	public JScrollPane getAssignSubjectPane() {
		return assignSubjectPane;
	}

	public void setAssignSubjectPane(JScrollPane assignSubjectPane) {
		this.assignSubjectPane = assignSubjectPane;
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

	public static String getNewstudentlabeltext() {
		return NEW_STUDENT_LABEL_TEXT;
	}

	public static String getEditstudentlabeltext() {
		return EDIT_STUDENT_LABEL_TEXT;
	}

	public void setEditStudent(Student student) {
		nameInput.setText(student.getName());
		surnameInput.setText(student.getSurname());
	}

}
