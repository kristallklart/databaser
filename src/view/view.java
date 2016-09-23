package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
import model.Course;
import model.Student;
import model.Studying;

public class view extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 5054166201282114423L;
	private JPanel contentPane;
	private final int BUTTON_WIDTH = 108;
	private final int BUTTON_HEIGHT = 23;
	private final int TEXTFIELD_WIDTH = 159;
	private final int TEXTFIELD_HEIGHT = 25;
	private final int LABEL_WIDTH = 108;
	private final int LABEL_HEIGHT = 23;
	private final int COMBOBOX_WIDHT = 85;
	private final int COMBOBOX_HEIGHT = 25;
	private final int RADIOBUTTON_WIDTH = 47;
	private final int RADIOBUTTON_HEIGHT = 23;
	private JTextField textField_stud_pnr;
	private JTextField textField_stud_name;
	private JTextField textField_stud_address;
	private JTextField textField_regStud_pnr;
	private JTextField textField_regStud_name;
	private JTextField textField_regGrade_name;
	private JTextField textField_regGrade_pnr;
	private JTextField textField_course_courseCode;
	private JTextField textField_course_cname;
	private JTextField textField_course_points;
	private JTextField textField_searchInfo_courseCode;
	private JTextField textField_searchInfo_pnr;
	private JTextField textField_searchInfo_grade;
	private JTable table_regStud;
	private JTable table_regGrade;
	private JTable table_searchInfo;
	private JTable table_caccess;
	private DefaultTableModel dtmcourses;

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
		setBounds(100, 100, 664, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 648, 391);
		contentPane.add(tabbedPane);

		// ***********************************
		// ***********STUDENT TAB*************
		// ***********************************

		JPanel panel_student = new JPanel();
		tabbedPane.addTab("Student", panel_student);
		panel_student.setLayout(null);

		JLabel lbl_feedback = new JLabel("Message: ");
		lbl_feedback.setBounds(7, 395, 638, 20);
		contentPane.add(lbl_feedback);

		JButton btn_stud_search = new JButton("Search");
		btn_stud_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Student st = new Student();
					st = Controller.getStudent(textField_stud_pnr.getText());
					textField_stud_name.setText(st.getSname());
					textField_stud_address.setText(st.getSaddress());

				} catch (SQLException e) {
					// TODO: handle exception : h�r b�r vi skriva ut n�got n�r
					// vi f�ngar ett exception
				}
			}
		});
		btn_stud_search.setBounds(292, 23, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_search);

		JButton btn_stud_clear = new JButton("Clear");
		btn_stud_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		btn_stud_clear.setBounds(10, 130, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_clear);

		JButton btn_stud_delete = new JButton("Delete");
		btn_stud_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_stud_delete.setBounds(255, 232, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_delete);

		JButton btn_stud_update = new JButton("Update");
		btn_stud_update.setBounds(375, 232, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_update);

		JButton btn_stud_save = new JButton("Save");
		btn_stud_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_stud_save.setBounds(495, 232, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_student.add(btn_stud_save);

		textField_stud_pnr = new JTextField();
		textField_stud_pnr.setBounds(121, 22, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_pnr);
		textField_stud_pnr.setColumns(10);

		textField_stud_name = new JTextField();
		textField_stud_name.setColumns(10);
		textField_stud_name.setBounds(121, 54, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_name);

		textField_stud_address = new JTextField();
		textField_stud_address.setColumns(10);
		textField_stud_address.setBounds(121, 86, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_student.add(textField_stud_address);

		JLabel lbl_stud_pnr = new JLabel("Personal number:");
		lbl_stud_pnr.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_pnr);

		JLabel lbl_stud_name = new JLabel("Name:");
		lbl_stud_name.setBounds(10, 54, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_name);

		JLabel lbl_stud_address = new JLabel("Address:");
		lbl_stud_address.setBounds(10, 86, LABEL_WIDTH, LABEL_HEIGHT);
		panel_student.add(lbl_stud_address);

		// ***********************************
		// *******REGISTER STUDENT TAB********
		// ***********************************

		JPanel panel_regStudent = new JPanel();
		tabbedPane.addTab("Register student", panel_regStudent);
		panel_regStudent.setLayout(null);

		JButton btn_regStud_delete = new JButton("Delete");
		btn_regStud_delete.setBounds(393, 301, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_delete);

		JButton btn_regStud_register = new JButton("Register");
		btn_regStud_register.setBounds(513, 301, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_register);

		JButton btn_regStud_search = new JButton("Search");
		btn_regStud_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Student st = new Student();
					st = Controller.getStudent(textField_regStud_pnr.getText());
					textField_regStud_name.setText(st.getSname());

				} catch (SQLException e) {
					System.out.println(e.getErrorCode());
				}
			}
		});
		btn_regStud_search.setBounds(325, 27, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_search);

		JButton btn_regStud_add = new JButton("Add");
		btn_regStud_add.setBounds(15, 212, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regStudent.add(btn_regStud_add);

		textField_regStud_pnr = new JTextField();
		textField_regStud_pnr.setColumns(10);
		textField_regStud_pnr.setBounds(154, 26, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regStudent.add(textField_regStud_pnr);

		textField_regStud_name = new JTextField();
		textField_regStud_name.setEditable(false);
		textField_regStud_name.setBounds(154, 60, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regStudent.add(textField_regStud_name);
		textField_regStud_name.setColumns(10);

		JLabel lbl_regStud_name = new JLabel("Name:");
		lbl_regStud_name.setBounds(15, 61, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_name);

		JLabel lbl_regStud_pnr = new JLabel("Personal number:");
		lbl_regStud_pnr.setBounds(15, 26, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_pnr);

		JLabel lbl_regStud_ccode = new JLabel("Course code:");
		lbl_regStud_ccode.setBounds(15, 95, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_ccode);

		JLabel lbl_regStud_year = new JLabel("Year");
		lbl_regStud_year.setBounds(154, 157, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regStudent.add(lbl_regStud_year);

		JComboBox comboBox_regStud_ccode = new JComboBox();
		comboBox_regStud_ccode.setMaximumRowCount(10);
		comboBox_regStud_ccode.setBounds(154, 98, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regStudent.add(comboBox_regStud_ccode);

		JComboBox comboBox_regStud_year = new JComboBox();
		comboBox_regStud_year.setBounds(226, 155, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regStudent.add(comboBox_regStud_year);

		JRadioButton rdbtn_regStud_ht = new JRadioButton("HT");
		rdbtn_regStud_ht.setBounds(15, 154, RADIOBUTTON_WIDTH, RADIOBUTTON_HEIGHT);
		panel_regStudent.add(rdbtn_regStud_ht);

		JRadioButton rdbtn_regStud_vt = new JRadioButton("VT");
		rdbtn_regStud_vt.setBounds(79, 154, RADIOBUTTON_WIDTH, RADIOBUTTON_HEIGHT);
		panel_regStudent.add(rdbtn_regStud_vt);

		JScrollPane scrollPane_regStud = new JScrollPane();
		scrollPane_regStud.setBounds(15, 243, 366, 81);
		panel_regStudent.add(scrollPane_regStud);

		table_regStud = new JTable();
		scrollPane_regStud.setViewportView(table_regStud);

		// ***********************************
		// *********REGISTER GRADE TAB********
		// ***********************************

		JPanel panel_regGrade = new JPanel();
		tabbedPane.addTab("Register grade", panel_regGrade);
		panel_regGrade.setLayout(null);

		DefaultTableModel dtmcourses = new DefaultTableModel();
		String[] course = { "Code", "Semester" };
		dtmcourses.setColumnIdentifiers(course);

		JButton btn_regGrade_search = new JButton("Search");
		btn_regGrade_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Student st = new Student();
					st = Controller.getStudent(textField_regGrade_pnr.getText());
					textField_regGrade_name.setText(st.getSname());

					ArrayList<Studying> s;
					s = Controller.getStudentStudying(textField_regGrade_pnr.getText());

					for (int i = 0; i < s.size(); i++) {
						String cCode = s.get(i).getcCode();
						String Semester = s.get(i).getSemester().toUpperCase();

						String[] studentsCourses = { cCode, Semester };

						dtmcourses.addRow(studentsCourses);

					}
					table_regGrade.setModel(dtmcourses);
				} catch (SQLException e) {
					System.out.println(e.getErrorCode());
				}

			}
		});

		btn_regGrade_search.setBounds(290, 28, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_search);

		JButton btn_regGrade_clear = new JButton("Clear");
		btn_regGrade_clear.setBounds(10, 298, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_clear);

		JButton btn_regGrade_save = new JButton("Save");
		btn_regGrade_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_regGrade_save.setBounds(280, 298, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_regGrade.add(btn_regGrade_save);

		textField_regGrade_pnr = new JTextField();
		textField_regGrade_pnr.setColumns(10);
		textField_regGrade_pnr.setBounds(119, 27, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regGrade.add(textField_regGrade_pnr);

		textField_regGrade_name = new JTextField();
		textField_regGrade_name.setEditable(false);
		textField_regGrade_name.setColumns(10);
		textField_regGrade_name.setBounds(119, 59, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_regGrade.add(textField_regGrade_name);

		JLabel lbl_regGrade_pnr = new JLabel("Personal number:");
		lbl_regGrade_pnr.setBounds(10, 27, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGrade_pnr);

		JLabel lbl_regGrade_name = new JLabel("Name:");
		lbl_regGrade_name.setBounds(10, 62, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGrade_name);

		JLabel lbl_regGarde_grade = new JLabel("Grade:");
		lbl_regGarde_grade.setBounds(10, 241, LABEL_WIDTH, LABEL_HEIGHT);
		panel_regGrade.add(lbl_regGarde_grade);

		JComboBox comboBox_regGrade_grade = new JComboBox();
		comboBox_regGrade_grade.setMaximumRowCount(10);
		comboBox_regGrade_grade.setBounds(119, 241, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_regGrade.add(comboBox_regGrade_grade);

		comboBox_regGrade_grade.addItem("");
		comboBox_regGrade_grade.addItem("A");
		comboBox_regGrade_grade.addItem("B");
		comboBox_regGrade_grade.addItem("C");
		comboBox_regGrade_grade.addItem("D");
		comboBox_regGrade_grade.addItem("E");
		comboBox_regGrade_grade.addItem("U");
		comboBox_regGrade_grade.setSelectedIndex(0);

		JScrollPane scrollPane_regGrade = new JScrollPane();
		scrollPane_regGrade.setBounds(119, 106, 237, 109);
		panel_regGrade.add(scrollPane_regGrade);

		table_regGrade = new JTable();
		scrollPane_regGrade.setViewportView(table_regGrade);
		table_regGrade.setModel(dtmcourses);
		table_regGrade.setRowHeight(20);

		// ***********************************
		// ************COURSE TAB*************
		// ***********************************

		JPanel panel_course = new JPanel();
		tabbedPane.addTab("Course", panel_course);
		panel_course.setLayout(null);

		JButton btn_course_clear = new JButton("Clear");
		btn_course_clear.setBounds(10, 185, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_clear);

		JButton btn_course_delete = new JButton("Delete");
		btn_course_delete.setBounds(254, 185, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_delete);

		JButton btn_course_update = new JButton("Update");
		btn_course_update.setBounds(366, 185, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_update);

		JButton btn_course_save = new JButton("Save");
		btn_course_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_course_save.setBounds(478, 185, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_save);

		JButton btn_course_search = new JButton("Search");
		btn_course_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					Course cc = new Course();
					cc = Controller.getCourse(textField_course_courseCode.getText());
					textField_course_cname.setText(cc.getCname());
					int i = cc.getCpoint();
					String string = Integer.toString(i);
					textField_course_points.setText(string);

				} catch (SQLException e) {
					// TODO: handle exception : h�r b�r vi skriva ut n�got n�r
					// vi f�ngar ett exception
				}
			}
		});
		btn_course_search.setBounds(273, 34, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_course.add(btn_course_search);

		textField_course_courseCode = new JTextField();
		textField_course_courseCode.setColumns(10);
		textField_course_courseCode.setBounds(107, 33, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_courseCode);

		textField_course_cname = new JTextField();
		textField_course_cname.setColumns(10);
		textField_course_cname.setBounds(107, 79, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_cname);

		textField_course_points = new JTextField();
		textField_course_points.setColumns(10);
		textField_course_points.setBounds(107, 128, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_course.add(textField_course_points);

		JLabel lbl_course_ccode = new JLabel("Course code:");
		lbl_course_ccode.setBounds(10, 33, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_ccode);

		JLabel lbl_course_name = new JLabel("Course name:");
		lbl_course_name.setBounds(10, 79, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_name);

		JLabel lbl_course_points = new JLabel("Points:");
		lbl_course_points.setBounds(10, 128, LABEL_WIDTH, LABEL_HEIGHT);
		panel_course.add(lbl_course_points);

		// ***********************************
		// *********SEARCH INFO TAB***********
		// ***********************************

		JPanel panel_searchInfo = new JPanel();
		tabbedPane.addTab("Search information", panel_searchInfo);
		panel_searchInfo.setLayout(null);

		JButton btn_searchInfo_search = new JButton("Search");
		btn_searchInfo_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_searchInfo_search.setBounds(298, 73, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_searchInfo.add(btn_searchInfo_search);

		textField_searchInfo_courseCode = new JTextField();
		textField_searchInfo_courseCode.setColumns(10);
		textField_searchInfo_courseCode.setBounds(127, 72, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_courseCode);

		textField_searchInfo_pnr = new JTextField();
		textField_searchInfo_pnr.setColumns(10);
		textField_searchInfo_pnr.setBounds(127, 120, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_pnr);

		textField_searchInfo_grade = new JTextField();
		textField_searchInfo_grade.setColumns(10);
		textField_searchInfo_grade.setBounds(127, 166, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel_searchInfo.add(textField_searchInfo_grade);

		JLabel lbl_searchInfo_selectOption = new JLabel("Select option:");
		lbl_searchInfo_selectOption.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_selectOption);

		JLabel lbl_searchInfo_courseCode = new JLabel("Course code:");
		lbl_searchInfo_courseCode.setBounds(10, 72, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_courseCode);

		JLabel lbl_searchInfo_personalNumber = new JLabel("Personal number:");
		lbl_searchInfo_personalNumber.setBounds(10, 120, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_personalNumber);

		JLabel lbl_searchInfo_grade = new JLabel("Grade:");
		lbl_searchInfo_grade.setBounds(10, 166, LABEL_WIDTH, LABEL_HEIGHT);
		panel_searchInfo.add(lbl_searchInfo_grade);

		JComboBox comboBox_searchInfo_selectOption = new JComboBox();
		comboBox_searchInfo_selectOption.setBounds(130, 22, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_searchInfo.add(comboBox_searchInfo_selectOption);

		JScrollPane scrollPane_searchInfo = new JScrollPane();
		scrollPane_searchInfo.setBounds(130, 212, 237, 109);
		panel_searchInfo.add(scrollPane_searchInfo);

		table_searchInfo = new JTable();
		scrollPane_searchInfo.setViewportView(table_searchInfo);

		// ***************************************
		// ***********CRONUS ACCESS TAB***********
		// ***************************************

		table_caccess = new JTable();
		JPanel panel_caccess = new JPanel();
		tabbedPane.addTab("Cronus Access", panel_caccess);
		panel_caccess.setLayout(null);

		JButton btn_caccess_go = new JButton("Go");
		btn_caccess_go.setBounds(216, 22, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel_caccess.add(btn_caccess_go);

		JLabel lbl_caccess_selectOption = new JLabel("Select query:");
		lbl_caccess_selectOption.setBounds(10, 22, LABEL_WIDTH, LABEL_HEIGHT);
		panel_caccess.add(lbl_caccess_selectOption);

		JComboBox comboBox_caccess_selectOption = new JComboBox();
		comboBox_caccess_selectOption.setBounds(119, 21, COMBOBOX_WIDHT, COMBOBOX_HEIGHT);
		panel_caccess.add(comboBox_caccess_selectOption);

		JScrollPane scrollPane_caccess = new JScrollPane();
		scrollPane_caccess.setBounds(130, 212, 237, 109);
		panel_caccess.add(scrollPane_caccess);

		table_caccess = new JTable();
		scrollPane_caccess.setViewportView(table_caccess);

		JSeparator separator_caccess = new JSeparator();
		separator_caccess.setBounds(10, 352, 599, 7);
		panel_caccess.add(separator_caccess);
	}
}
