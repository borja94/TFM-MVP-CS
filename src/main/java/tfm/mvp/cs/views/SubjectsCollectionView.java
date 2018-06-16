package tfm.mvp.cs.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;

import tfm.mvp.cs.presenters.ISubjectCollectionViewPresenter;

public class SubjectsCollectionView extends JPanel {

	private TableModel subjectsTableModel;

	private JButton deleteButton;
	private JButton editButton;
	private JButton newSubjectButton;
	private JTable subjectTable;
	private JScrollPane tableScrollPane;

	private ISubjectCollectionViewPresenter iSubjectsCollectionViewPresenter;

	public SubjectsCollectionView(ISubjectCollectionViewPresenter subjectCollectionViewPresenter) {

		iSubjectsCollectionViewPresenter = subjectCollectionViewPresenter;
		subjectCollectionViewPresenter.setSubjectCollectionView(this);
		initComponents();
		updateSubjectsTableData();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		subjectTable = new JTable();
		deleteButton = new JButton();
		editButton = new JButton();
		newSubjectButton = new JButton();

		tableScrollPane.setViewportView(subjectTable);

		deleteButton.setText("Borrar");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onDeleteButtonActionPerformed();
			}
		});

		editButton.setText("Modo edición");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onEditButtonActionPerformed();
			}
		});

		newSubjectButton.setText("Nueva asignatura");
		newSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onNewSubjectButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(editButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newSubjectButton)))
				.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteButton).addComponent(editButton).addComponent(newSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void updateSubjectsTableData() {
		iSubjectsCollectionViewPresenter.updateSubjectsTableData();
	}

	private void onDeleteButtonActionPerformed() {
		iSubjectsCollectionViewPresenter.removeSubject();
	}

	private void onEditButtonActionPerformed() {

		iSubjectsCollectionViewPresenter.editMode();
	}

	private void onNewSubjectButtonActionPerformed() {
		iSubjectsCollectionViewPresenter.newSubjectMode();
	}

	public TableModel getSubjectsTableModel() {
		return subjectsTableModel;
	}

	public void setSubjectsTableModel(TableModel subjectsTableModel) {
		this.subjectsTableModel = subjectsTableModel;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public JButton getNewSubjectButton() {
		return newSubjectButton;
	}

	public void setNewSubjectButton(JButton newSubjectButton) {
		this.newSubjectButton = newSubjectButton;
	}

	public JTable getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(JTable subjectTable) {
		this.subjectTable = subjectTable;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}
	
	

}
