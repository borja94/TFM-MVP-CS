package tfm.mvp.cs.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;

import tfm.mvp.cs.presenters.ITeacherCollectionViewPresenter;

public class TeacherCollectionView extends JPanel {

	private JButton deleteTeacherButton;
	private JButton editTeacherButton;
	private JButton newTeacherButton;
	private JTable teachersTable;
	private JScrollPane tableScrollPane;

	private ITeacherCollectionViewPresenter iTeacherCollectionPresenter;
	private TableModel teachersTableModel;

	public TeacherCollectionView(ITeacherCollectionViewPresenter teacherCollectionViewPresenter) {
		iTeacherCollectionPresenter = teacherCollectionViewPresenter;
		iTeacherCollectionPresenter.setTeacherCollectionView(this);

		initComponents();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		teachersTable = new JTable();
		deleteTeacherButton = new JButton();
		editTeacherButton = new JButton();
		newTeacherButton = new JButton();

		updateTeacherTableData();

		tableScrollPane.setViewportView(teachersTable);

		deleteTeacherButton.setText("Borrar");
		deleteTeacherButton.addActionListener(e -> onDeleteTeacherButtonActionPerformed());

		editTeacherButton.setText("Modo edición");
		editTeacherButton.addActionListener(e -> onEditTeacherButtonActionPerformed());

		newTeacherButton.setText("Nuevo profesor");
		newTeacherButton.addActionListener(e -> onNewTeacherButtonActionPerformed());

		initComponentPostions();
	}

	private void initComponentPostions() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteTeacherButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(editTeacherButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newTeacherButton)))
				.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteTeacherButton).addComponent(editTeacherButton)
								.addComponent(newTeacherButton))
						.addGap(203, 203, 203)));
	}

	public void updateTeacherTableData() {
		iTeacherCollectionPresenter.updateTeacherTableData();
	}

	private void onDeleteTeacherButtonActionPerformed() {
		iTeacherCollectionPresenter.deleteTeacher();
	}

	private void onEditTeacherButtonActionPerformed() {
		iTeacherCollectionPresenter.editTeacher();
	}

	private void onNewTeacherButtonActionPerformed() {
		iTeacherCollectionPresenter.newTeacher();
	}

	public JTable getTeachersTable() {
		return teachersTable;
	}

	public TableModel getTeachersTableModel() {
		return teachersTableModel;
	}

	public void setTeachersTableModel(TableModel teachersTableModel) {
		this.teachersTableModel = teachersTableModel;
	}

}
