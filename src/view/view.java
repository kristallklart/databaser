package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Student;

public class view extends JFrame {

	private JPanel contentPane;
	private JTextField textField_stud_pnr;
	private JTextField txtField_name;
	private JTextField txtField_adress;
	private JTextField textField_postelCode;
	private JTextField textField_city;
	private JTextField textField_country;
	private JTextField textField_course_courseCode;
	private JTextField textField_course_courseName;
	private JTextField textField_course_points;
	private JTextField textField_rgrade_pnr;
	private JTable table_rgrade;
	private JTextField textField_sinfo_courseCode;
	private JTextField textField_sinfo_pnr;
	private JTextField textField_sinfo_grade;
	private JTable table_sinfo;
	private JTextField textField_rgstud_pnr;
	private JTable table_rgStud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					view frame = new view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 648, 480);
		contentPane.add(tabbedPane);

		JPanel panel_student = new JPanel();
		tabbedPane.addTab("Student", null, panel_student, null);
		panel_student.setLayout(null);

		JLabel lbl_stud_pnr = new JLabel("Personal number:");
		lbl_stud_pnr.setBounds(10, 22, 112, 21);
		panel_student.add(lbl_stud_pnr);

		textField_stud_pnr = new JTextField();
		textField_stud_pnr.setBounds(121, 22, 120, 20);
		panel_student.add(textField_stud_pnr);
		textField_stud_pnr.setColumns(10);

		JLabel lbl_stud_name = new JLabel("Name:");
		lbl_stud_name.setBounds(10, 54, 112, 21);
		panel_student.add(lbl_stud_name);

		JLabel lbl_stud_adress = new JLabel("Adress:");
		lbl_stud_adress.setBounds(10, 86, 112, 21);
		panel_student.add(lbl_stud_adress);

		JLabel lbl_stud_postelCode = new JLabel("postal code:");
		lbl_stud_postelCode.setBounds(10, 118, 112, 21);
		panel_student.add(lbl_stud_postelCode);

		JLabel lbl_stud_city = new JLabel("City:");
		lbl_stud_city.setBounds(10, 152, 112, 21);
		panel_student.add(lbl_stud_city);

		JLabel lbl_stud_country = new JLabel("Country:");
		lbl_stud_country.setBounds(10, 184, 112, 21);
		panel_student.add(lbl_stud_country);

		txtField_name = new JTextField();
		txtField_name.setColumns(10);
		txtField_name.setBounds(121, 54, 120, 20);
		panel_student.add(txtField_name);

		txtField_adress = new JTextField();
		txtField_adress.setColumns(10);
		txtField_adress.setBounds(121, 86, 120, 20);
		panel_student.add(txtField_adress);

		textField_postelCode = new JTextField();
		textField_postelCode.setColumns(10);
		textField_postelCode.setBounds(121, 118, 120, 20);
		panel_student.add(textField_postelCode);

		textField_city = new JTextField();
		textField_city.setColumns(10);
		textField_city.setBounds(121, 152, 120, 20);
		panel_student.add(textField_city);

		textField_country = new JTextField();
		textField_country.setColumns(10);
		textField_country.setBounds(121, 184, 120, 20);
		panel_student.add(textField_country);

		JButton btn_stud_search = new JButton("Search");
		btn_stud_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Student st = new Student();
					st = Controller.getStudent(textField_stud_pnr.getText());
					textField_city.setText(st.getScity());

				} finally {

				}
			}
		});
		btn_stud_search.setBounds(272, 21, 89, 23);
		panel_student.add(btn_stud_search);

		JButton btn_stud_clear = new JButton("Clear");
		btn_stud_clear.setBounds(10, 232, 77, 23);
		panel_student.add(btn_stud_clear);

		JButton btn_stud_delete = new JButton("Delete");
		btn_stud_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_stud_delete.setBounds(272, 232, 89, 23);
		panel_student.add(btn_stud_delete);

		JButton btn_stud_update = new JButton("Update");
		btn_stud_update.setBounds(378, 232, 89, 23);
		panel_student.add(btn_stud_update);

		JButton btn_stud_register = new JButton("Save");
		btn_stud_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_stud_register.setBounds(490, 232, 97, 23);
		panel_student.add(btn_stud_register);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 337, 599, 7);
		panel_student.add(separator_1);

		JLabel lbl_stud_feedback = new JLabel("");
		lbl_stud_feedback.setBounds(18, 360, 591, 52);
		panel_student.add(lbl_stud_feedback);

		JPanel panel_registerStudent = new JPanel();
		tabbedPane.addTab("Register student", null, panel_registerStudent, null);
		panel_registerStudent.setLayout(null);

		JLabel lbl_rgStud_pnr = new JLabel("Personal number:");
		lbl_rgStud_pnr.setBounds(15, 26, 134, 21);
		panel_registerStudent.add(lbl_rgStud_pnr);

		textField_rgstud_pnr = new JTextField();
		textField_rgstud_pnr.setColumns(10);
		textField_rgstud_pnr.setBounds(154, 26, 120, 20);
		panel_registerStudent.add(textField_rgstud_pnr);

		JLabel lbl_rgStud_name = new JLabel("Name");
		lbl_rgStud_name.setBounds(154, 59, 120, 23);
		panel_registerStudent.add(lbl_rgStud_name);

		JLabel lbl_rgStud_ccode = new JLabel("Course code:");
		lbl_rgStud_ccode.setBounds(15, 95, 103, 21);
		panel_registerStudent.add(lbl_rgStud_ccode);

		JComboBox comboBox_rgStud_ccode = new JComboBox();
		comboBox_rgStud_ccode.setBounds(154, 98, 120, 26);
		panel_registerStudent.add(comboBox_rgStud_ccode);

		JRadioButton rdbtn_rgStud_ht = new JRadioButton("HT");
		rdbtn_rgStud_ht.setBounds(15, 154, 53, 29);
		panel_registerStudent.add(rdbtn_rgStud_ht);

		JRadioButton rdbtn_rgStud_vt = new JRadioButton("VT");
		rdbtn_rgStud_vt.setBounds(79, 154, 53, 29);
		panel_registerStudent.add(rdbtn_rgStud_vt);

		JComboBox comboBox_rgStud_år = new JComboBox();
		comboBox_rgStud_år.setBounds(187, 155, 87, 26);
		panel_registerStudent.add(comboBox_rgStud_år);

		JLabel lbl_rgStud_år = new JLabel("\u00C5r");
		lbl_rgStud_år.setBounds(154, 158, 26, 20);
		panel_registerStudent.add(lbl_rgStud_år);

		JScrollPane scrollPane_rgStud = new JScrollPane();
		scrollPane_rgStud.setBounds(15, 243, 366, 81);
		panel_registerStudent.add(scrollPane_rgStud);

		table_rgStud = new JTable();
		scrollPane_rgStud.setViewportView(table_rgStud);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(15, 353, 599, 7);
		panel_registerStudent.add(separator_4);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(15, 361, 591, 52);
		panel_registerStudent.add(label_3);

		JButton btn_rgStud_delete = new JButton("Delete");
		btn_rgStud_delete.setBounds(410, 302, 89, 23);
		panel_registerStudent.add(btn_rgStud_delete);

		JButton btn_rgStud_register = new JButton("Register");
		btn_rgStud_register.setBounds(517, 301, 89, 23);
		panel_registerStudent.add(btn_rgStud_register);

		JButton btn_regStud_search = new JButton("Search");
		btn_regStud_search.setBounds(305, 22, 89, 23);
		panel_registerStudent.add(btn_regStud_search);

		JButton btn_rgStud_add = new JButton("Add");
		btn_rgStud_add.setBounds(15, 212, 89, 23);
		panel_registerStudent.add(btn_rgStud_add);

		JPanel panel_rgrade = new JPanel();
		tabbedPane.addTab("Register grade", null, panel_rgrade, null);
		panel_rgrade.setLayout(null);

		JLabel lbl_rgrade_pnr = new JLabel("Personal number:");
		lbl_rgrade_pnr.setBounds(10, 27, 111, 21);
		panel_rgrade.add(lbl_rgrade_pnr);

		textField_rgrade_pnr = new JTextField();
		textField_rgrade_pnr.setColumns(10);
		textField_rgrade_pnr.setBounds(119, 27, 120, 20);
		panel_rgrade.add(textField_rgrade_pnr);

		JButton btn_rgrade_search = new JButton("Search");
		btn_rgrade_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_rgrade_search.setBounds(267, 26, 89, 23);
		panel_rgrade.add(btn_rgrade_search);

		JLabel lbl_rgrade_name = new JLabel("Name");
		lbl_rgrade_name.setBounds(119, 59, 120, 23);
		panel_rgrade.add(lbl_rgrade_name);

		JScrollPane scrollPane_rgrade = new JScrollPane();
		scrollPane_rgrade.setBounds(119, 106, 237, 109);
		panel_rgrade.add(scrollPane_rgrade);

		table_rgrade = new JTable();

		scrollPane_rgrade.setViewportView(table_rgrade);
		Object[] columns = { "Ccode", "Cname", "Points" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table_rgrade.setModel(model);
		table_rgrade.setRowHeight(20);

		JLabel lbl_rgarde_grade = new JLabel("Grade:");
		lbl_rgarde_grade.setBounds(10, 241, 94, 21);
		panel_rgrade.add(lbl_rgarde_grade);

		JComboBox comboBox_rgrade_grade = new JComboBox();
		comboBox_rgrade_grade.setBounds(119, 241, 146, 20);
		panel_rgrade.add(comboBox_rgrade_grade);

		comboBox_rgrade_grade.addItem("");
		comboBox_rgrade_grade.addItem("A");
		comboBox_rgrade_grade.addItem("B");
		comboBox_rgrade_grade.addItem("C");
		comboBox_rgrade_grade.addItem("D");
		comboBox_rgrade_grade.addItem("E");
		comboBox_rgrade_grade.addItem("U");
		comboBox_rgrade_grade.setSelectedIndex(0);

		JButton btn_rgrade_clear = new JButton("Clear");
		btn_rgrade_clear.setBounds(10, 298, 77, 23);
		panel_rgrade.add(btn_rgrade_clear);

		JButton btn_rgrade_save = new JButton("Save");
		btn_rgrade_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_rgrade_save.setBounds(280, 298, 89, 23);
		panel_rgrade.add(btn_rgrade_save);

		JButton btn_rgrade_add = new JButton("Add");
		btn_rgrade_add.setBounds(267, 58, 89, 23);
		panel_rgrade.add(btn_rgrade_add);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 337, 599, 7);
		panel_rgrade.add(separator);

		JLabel lbl_rgrade_feedback = new JLabel("");
		lbl_rgrade_feedback.setBounds(10, 361, 591, 52);
		panel_rgrade.add(lbl_rgrade_feedback);

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", null, panel_course, null);
		panel_course.setLayout(null);

		JLabel lbl_course_courseCode = new JLabel("Course code:");
		lbl_course_courseCode.setBounds(10, 33, 75, 21);
		panel_course.add(lbl_course_courseCode);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(107, 33, 120, 20);
		panel_course.add(textField_course_courseCode);

		JLabel lbl_course_courseName = new JLabel("Course name:");
		lbl_course_courseName.setBounds(10, 79, 89, 21);
		panel_course.add(lbl_course_courseName);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 128, 75, 21);
		panel_course.add(lbl_course_points);

		textField_course_courseName = new JTextField();
		textField_course_courseName.setColumns(10);
		textField_course_courseName.setBounds(107, 79, 120, 20);
		panel_course.add(textField_course_courseName);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(107, 128, 120, 20);
		panel_course.add(textField_course_points);

		JButton btn_course_clear = new JButton("Clear");
		btn_course_clear.setBounds(10, 185, 77, 23);
		panel_course.add(btn_course_clear);

		JButton btn_course_delete = new JButton("Delete");
		btn_course_delete.setBounds(254, 185, 89, 23);
		panel_course.add(btn_course_delete);

		JButton btn_course_update = new JButton("Update");
		btn_course_update.setBounds(366, 185, 89, 23);
		panel_course.add(btn_course_update);

		JButton btn_course_save = new JButton("Save");
		btn_course_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_course_save.setBounds(478, 185, 89, 23);
		panel_course.add(btn_course_save);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 333, 599, 7);
		panel_course.add(separator_2);

		JLabel lbl_course_feedback = new JLabel("");
		lbl_course_feedback.setBounds(10, 356, 591, 52);
		panel_course.add(lbl_course_feedback);

		JButton btn_course_search = new JButton("Search");
		btn_course_search.setBounds(254, 32, 89, 23);
		panel_course.add(btn_course_search);

		JPanel panel_searchinfo = new JPanel();
		tabbedPane.addTab("Search information", null, panel_searchinfo, null);
		panel_searchinfo.setLayout(null);

		JLabel lbl_selectOption = new JLabel("Select option:");
		lbl_selectOption.setBounds(10, 22, 94, 21);
		panel_searchinfo.add(lbl_selectOption);

		JComboBox comboBox_sinfo_selectOption = new JComboBox();
		comboBox_sinfo_selectOption.setBounds(130, 22, 117, 20);
		panel_searchinfo.add(comboBox_sinfo_selectOption);

		JLabel lbl_sinfo_courseCode = new JLabel("Course code:");
		lbl_sinfo_courseCode.setBounds(10, 72, 84, 21);
		panel_searchinfo.add(lbl_sinfo_courseCode);

		JLabel lbl_sinfo_personalNumber = new JLabel("Personal number:");
		lbl_sinfo_personalNumber.setBounds(10, 120, 107, 21);
		panel_searchinfo.add(lbl_sinfo_personalNumber);

		JLabel lbl_sinfo_grade = new JLabel("Grade:");
		lbl_sinfo_grade.setBounds(10, 166, 84, 21);
		panel_searchinfo.add(lbl_sinfo_grade);

		textField_sinfo_courseCode = new JTextField();
		textField_sinfo_courseCode.setColumns(10);
		textField_sinfo_courseCode.setBounds(127, 72, 120, 20);
		panel_searchinfo.add(textField_sinfo_courseCode);

		textField_sinfo_pnr = new JTextField();
		textField_sinfo_pnr.setColumns(10);
		textField_sinfo_pnr.setBounds(127, 120, 120, 20);
		panel_searchinfo.add(textField_sinfo_pnr);

		textField_sinfo_grade = new JTextField();
		textField_sinfo_grade.setColumns(10);
		textField_sinfo_grade.setBounds(127, 166, 120, 20);
		panel_searchinfo.add(textField_sinfo_grade);

		JButton btn_sinfo_search = new JButton("Search");
		btn_sinfo_search.setBounds(271, 71, 89, 23);
		panel_searchinfo.add(btn_sinfo_search);

		JScrollPane scrollPane_sinfo = new JScrollPane();
		scrollPane_sinfo.setBounds(130, 212, 237, 109);
		panel_searchinfo.add(scrollPane_sinfo);

		table_sinfo = new JTable();
		scrollPane_sinfo.setViewportView(table_sinfo);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 352, 599, 7);
		panel_searchinfo.add(separator_3);

		JLabel lbl_sinfo_feedback = new JLabel("");
		lbl_sinfo_feedback.setBounds(10, 364, 591, 52);
		panel_searchinfo.add(lbl_sinfo_feedback);

	}
}
