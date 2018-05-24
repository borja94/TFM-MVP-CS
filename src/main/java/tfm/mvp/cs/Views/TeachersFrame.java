/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.cs.Views;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.JButton;

/**
 *
 * @author borja
 */
public class TeachersFrame extends JFrame {

	private MenuFrame menuFrame;
	private TeacherFormView teacherFormView;
	private TeacherCollectionView teacherCollectionView;

	/**
	 * Creates new form TeachersFrame
	 * 
	 * @param menuFrame
	 */
	public TeachersFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
		teacherFormView = new TeacherFormView();
		teacherCollectionView = new TeacherCollectionView(teacherFormView);
		teacherFormView.setTeacherCollectionPresenter(teacherCollectionView.getTeacherCollectionPresenter());
		initComponents();
	}

	private void initComponents() {

		JButton returnMenuFrameButton=new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		returnMenuFrameButton.setText("Volver");
		returnMenuFrameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnMenuFrameButtonActionPerformed();
			}
		});

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(teacherFormView);
		container.add(teacherCollectionView);
		this.setContentPane(container);
		this.add(returnMenuFrameButton);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(returnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(teacherFormView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherCollectionView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(teacherFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(returnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void returnMenuFrameButtonActionPerformed() {
		this.setVisible(false);
		menuFrame.setVisible(true);
		dispose();
	}

}
