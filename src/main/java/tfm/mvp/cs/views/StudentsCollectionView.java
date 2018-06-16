package tfm.mvp.cs.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import tfm.mvp.cs.presenters.IStudentCollectionViewPresenter;

public class StudentsCollectionView extends JPanel {

	private TableModel studentsTableModel;

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JButton newStudentButton;
	private JTable studentsTable;
	private JScrollPane tableScrollPane;
	private IStudentCollectionViewPresenter iStudentCollectionViewPresenter;

	public StudentsCollectionView(IStudentCollectionViewPresenter studentCollectionViewPresenter) {
		iStudentCollectionViewPresenter = studentCollectionViewPresenter;
		iStudentCollectionViewPresenter.setStudentCollectionView(this);
		initComponents();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		studentsTable = new JTable();
		deleteStudentButton = new JButton();
		editStudentButton = new JButton();
		newStudentButton = new JButton();

		updateStudentTableData();

		tableScrollPane.setViewportView(studentsTable);

		deleteStudentButton.setText("Borrar");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onDeleteStudentButtonActionPerformed();
			}
		});

		editStudentButton.setText("Modo edici�n");
		editStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onEditStudentButtonActionPerformed();
			}
		});

		newStudentButton.setText("Nuevo alumno");
		newStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onNewStudentButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel2Layout = new GroupLayout(this);
		this.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(deleteStudentButton)
										.addGap(18, 18, 18).addComponent(editStudentButton).addGap(18, 18, 18)
										.addComponent(newStudentButton)))
						.addContainerGap(17, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteStudentButton).addComponent(editStudentButton)
								.addComponent(newStudentButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	public void updateStudentTableData() {
		iStudentCollectionViewPresenter.updateStudentTableData();
	}

	private void onDeleteStudentButtonActionPerformed() {
		iStudentCollectionViewPresenter.removeStudent();
	}

	private void onEditStudentButtonActionPerformed() {

		iStudentCollectionViewPresenter.editStudentMode();
	}

	private void onNewStudentButtonActionPerformed() {
		iStudentCollectionViewPresenter.newStudentMode();
	}

	public TableModel getStudentsTableModel() {
		return studentsTableModel;
	}

	public void setStudentsTableModel(TableModel studentsTableModel) {
		this.studentsTableModel = studentsTableModel;
	}

	public JButton getDeleteStudentButton() {
		return deleteStudentButton;
	}

	public void setDeleteStudentButton(JButton deleteStudentButton) {
		this.deleteStudentButton = deleteStudentButton;
	}

	public JButton getEditStudentButton() {
		return editStudentButton;
	}

	public void setEditStudentButton(JButton editStudentButton) {
		this.editStudentButton = editStudentButton;
	}

	public JButton getNewStudentButton() {
		return newStudentButton;
	}

	public void setNewStudentButton(JButton newStudentButton) {
		this.newStudentButton = newStudentButton;
	}

	public JTable getStudentsTable() {
		return studentsTable;
	}

	public void setStudentsTable(JTable studentsTable) {
		this.studentsTable = studentsTable;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}
	
	
	
	

}
