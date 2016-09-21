package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

		JButton btn_stud_register = new JButton("Register New");
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

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", null, panel_course, null);
		panel_course.setLayout(null);

		JLabel lbl_course_courseCode = new JLabel("Course code:");
		lbl_course_courseCode.setBounds(10, 33, 75, 21);
		panel_course.add(lbl_course_courseCode);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(93, 33, 120, 20);
		panel_course.add(textField_course_courseCode);

		JLabel lbl_course_courseName = new JLabel("Course name:");
		lbl_course_courseName.setBounds(10, 79, 75, 21);
		panel_course.add(lbl_course_courseName);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 128, 75, 21);
		panel_course.add(lbl_course_points);

		textField_course_courseName = new JTextField();
		textField_course_courseName.setColumns(10);
		textField_course_courseName.setBounds(93, 79, 120, 20);
		panel_course.add(textField_course_courseName);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(93, 128, 120, 20);
		panel_course.add(textField_course_points);

		JButton btn_course_search = new JButton("Search");
		btn_course_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_course_search.setBounds(230, 32, 89, 23);
		panel_course.add(btn_course_search);

		JButton btn_course_clear = new JButton("Clear");
		btn_course_clear.setBounds(10, 185, 77, 23);
		panel_course.add(btn_course_clear);

		JButton btn_course_delete = new JButton("Delete");
		btn_course_delete.setBounds(243, 185, 89, 23);
		panel_course.add(btn_course_delete);

		JButton btn_course_update = new JButton("Update");
		btn_course_update.setBounds(353, 185, 89, 23);
		panel_course.add(btn_course_update);

		JButton btn_course_save = new JButton("Save");
		btn_course_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_course_save.setBounds(465, 185, 89, 23);
		panel_course.add(btn_course_save);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 333, 599, 7);
		panel_course.add(separator_2);

		JLabel lbl_course_feedback = new JLabel("");
		lbl_course_feedback.setBounds(10, 356, 591, 52);
		panel_course.add(lbl_course_feedback);

		JPanel panel_rgrade = new JPanel();
		tabbedPane.addTab("Register grade", null, panel_rgrade, null);
		panel_rgrade.setLayout(null);

		JLabel lbl_rgrade_pnr = new JLabel("Personal number:");
		lbl_rgrade_pnr.setBounds(10, 27, 94, 21);
		panel_rgrade.add(lbl_rgrade_pnr);

		textField_rgrade_pnr = new JTextField();
		textField_rgrade_pnr.setColumns(10);
		textField_rgrade_pnr.setBounds(107, 27, 120, 20);
		panel_rgrade.add(textField_rgrade_pnr);

		JButton btn_rgrade_search = new JButton("Search");
		btn_rgrade_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_rgrade_search.setBounds(249, 26, 89, 23);
		panel_rgrade.add(btn_rgrade_search);

		JLabel lbl_rgrade_name = new JLabel("Name");
		lbl_rgrade_name.setBounds(107, 58, 120, 23);
		panel_rgrade.add(lbl_rgrade_name);

		JScrollPane scrollPane_rgrade = new JScrollPane();
		scrollPane_rgrade.setBounds(107, 111, 237, 109);
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
		comboBox_rgrade_grade.setBounds(107, 241, 146, 20);
		panel_rgrade.add(comboBox_rgrade_grade);

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
		btn_rgrade_add.setBounds(249, 60, 89, 23);
		panel_rgrade.add(btn_rgrade_add);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 337, 599, 7);
		panel_rgrade.add(separator);

		JLabel lbl_rgrade_feedback = new JLabel("");
		lbl_rgrade_feedback.setBounds(10, 361, 591, 52);
		panel_rgrade.add(lbl_rgrade_feedback);

		JPanel panel_searchinfo = new JPanel();
		tabbedPane.addTab("Search information", null, panel_searchinfo, null);
		panel_searchinfo.setLayout(null);

		JLabel lbl_selectOption = new JLabel("Select option:");
		lbl_selectOption.setBounds(10, 22, 94, 21);
		panel_searchinfo.add(lbl_selectOption);

		JComboBox comboBox_sinfo_selectOption = new JComboBox();
		comboBox_sinfo_selectOption.setBounds(99, 22, 117, 20);
		panel_searchinfo.add(comboBox_sinfo_selectOption);

		JLabel lbl_sinfo_courseCode = new JLabel("Course code:");
		lbl_sinfo_courseCode.setBounds(10, 72, 84, 21);
		panel_searchinfo.add(lbl_sinfo_courseCode);

		JLabel lbl_sinfo_personalNumber = new JLabel("Personal number:");
		lbl_sinfo_personalNumber.setBounds(10, 120, 84, 21);
		panel_searchinfo.add(lbl_sinfo_personalNumber);

		JLabel lbl_sinfo_grade = new JLabel("Grade:");
		lbl_sinfo_grade.setBounds(10, 166, 84, 21);
		panel_searchinfo.add(lbl_sinfo_grade);

		textField_sinfo_courseCode = new JTextField();
		textField_sinfo_courseCode.setColumns(10);
		textField_sinfo_courseCode.setBounds(99, 72, 120, 20);
		panel_searchinfo.add(textField_sinfo_courseCode);

		textField_sinfo_pnr = new JTextField();
		textField_sinfo_pnr.setColumns(10);
		textField_sinfo_pnr.setBounds(99, 120, 120, 20);
		panel_searchinfo.add(textField_sinfo_pnr);

		textField_sinfo_grade = new JTextField();
		textField_sinfo_grade.setColumns(10);
		textField_sinfo_grade.setBounds(99, 166, 120, 20);
		panel_searchinfo.add(textField_sinfo_grade);

		JButton btn_sinfo_search = new JButton("Search");
		btn_sinfo_search.setBounds(238, 71, 89, 23);
		panel_searchinfo.add(btn_sinfo_search);

		JScrollPane scrollPane_sinfo = new JScrollPane();
		scrollPane_sinfo.setBounds(99, 216, 237, 109);
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
